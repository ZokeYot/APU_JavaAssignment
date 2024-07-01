package Repository;

import Model.Class.Patient;
import Model.Class.PaymentMethod;
import Exception.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentMethodRepo {

    private final Map<UUID, PaymentMethod> paymentMethodRepo;
    private final PatientRepo patientRepo;
    private final Scanner scanner;

    public PaymentMethodRepo(PatientRepo patientRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.patientRepo = patientRepo;
        paymentMethodRepo = new HashMap<>();
        scanner = new Scanner(new File("src\\Text Files\\payment method.txt"));
        readFile();
    }

    private void readFile() throws ResourceNotFoundException{
        System.out.println("Reading Payment Method File.....");
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().trim().split("\\|");

            String paymentMethodId = line[0];
            String patientId = line[1];
            String title = line[2];
            String cardNumber = line[3];
            String bank = line[4];
            String validUntil = line[5];

            Patient patient = patientRepo.find(patientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Patient Not Found !!"));

            PaymentMethod paymentMethod = new PaymentMethod(patient, paymentMethodId, title, cardNumber, bank, validUntil);

            paymentMethodRepo.put(paymentMethod.getPaymentMethodId() , paymentMethod);
        }
    }

    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\payment method.txt"));
        for(PaymentMethod paymentMethod : paymentMethodRepo.values()){
            fileWriter.write(paymentMethod.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }


    public List<PaymentMethod> getPatientPaymentMethod(Patient patient){
        return paymentMethodRepo.values().stream()
                .filter(paymentInformation -> paymentInformation.getPatient().equals(patient))
                .collect(Collectors.toList());
    }

    public Optional<PaymentMethod> find(UUID paymentMethodId){ return Optional.ofNullable(paymentMethodRepo.get(paymentMethodId));}

    public Optional<PaymentMethod> find(String paymentMethodId){ return Optional.ofNullable(paymentMethodRepo.get(UUID.fromString(paymentMethodId)));}

    public void create(PaymentMethod paymentMethod) throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\payment method.txt" , true));

        fileWriter.write(paymentMethod.toString());
        fileWriter.newLine();

        fileWriter.close();
        paymentMethodRepo.put(paymentMethod.getPaymentMethodId(), paymentMethod);
        getPatientPaymentMethod(paymentMethod.getPatient()).add(paymentMethod);
    }

    public void update(UUID paymentMethodId, PaymentMethod newPaymentMethod) throws ResourceNotFoundException, IOException{
        PaymentMethod paymentMethod = find(paymentMethodId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Information Not Found"));

        paymentMethodRepo.replace(paymentMethodId, newPaymentMethod);
        updateFile();
    }

    public void delete(UUID paymentMethodId) throws IOException, ResourceNotFoundException{
        PaymentMethod paymentMethod = find(paymentMethodId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Information Not Found"));
        paymentMethodRepo.remove(paymentMethodId);
        updateFile();
    }

    public void deleteAll(UUID patientId) throws IOException{
        paymentMethodRepo.entrySet()
                .removeIf(entry -> entry.getValue().getPatient().getUserID().equals(patientId));
        updateFile();
    }
}
