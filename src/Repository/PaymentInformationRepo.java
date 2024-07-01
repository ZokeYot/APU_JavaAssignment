package Repository;

import Model.Class.Patient;
import Model.Class.PaymentInformation;
import Exception.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class PaymentInformationRepo {

    private final Map<UUID, PaymentInformation> paymentInformationMap;
    private final PatientRepo patientRepo;
    private final Scanner scanner;

    public PaymentInformationRepo(PatientRepo patientRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.patientRepo = patientRepo;
        paymentInformationMap = new HashMap<>();
        scanner = new Scanner(new File("src\\Text Files\\payment information.txt"));
        readFile();
    }

    private void readFile() throws ResourceNotFoundException{
        System.out.println("Reading Payment Information File.....");
        while(scanner.hasNextLine()){
            String[] line = scanner.nextLine().trim().split("\\|");

            String paymentInformationId = line[0];
            String patientId = line[1];
            String cardNumber = line[2];
            String bank = line[3];
            String validUntil = line[4];

            Patient patient = patientRepo.find(patientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Patient Not Found !!"));

            PaymentInformation paymentInformation = new PaymentInformation(patient, paymentInformationId, cardNumber, bank, validUntil);

            paymentInformationMap.put(paymentInformation.getPaymentInformationId() , paymentInformation);
        }
    }

    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\payment information.txt"));
        for(PaymentInformation paymentInformation : paymentInformationMap.values()){
            fileWriter.write(paymentInformation.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }


    public List<PaymentInformation> getPatientPaymentInformation(Patient patient){
        return paymentInformationMap.values().stream()
                .filter(paymentInformation -> paymentInformation.getPatient().equals(patient))
                .collect(Collectors.toList());
    }

    public Optional<PaymentInformation> find(UUID paymentInformationId){ return Optional.ofNullable(paymentInformationMap.get(paymentInformationId));}

    public Optional<PaymentInformation> find(String paymentInformationId){ return Optional.ofNullable(paymentInformationMap.get(UUID.fromString(paymentInformationId)));}

    public void create(PaymentInformation paymentInformation) throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\payment information.txt" , true));

        fileWriter.write(paymentInformation.toString());
        fileWriter.newLine();

        fileWriter.close();
        paymentInformationMap.put(paymentInformation.getPaymentInformationId(), paymentInformation);
        getPatientPaymentInformation(paymentInformation.getPatient()).add(paymentInformation);
    }

    public void update(UUID paymentInformationId, PaymentInformation newPaymentInformation) throws ResourceNotFoundException, IOException{
        PaymentInformation paymentInformation = find(paymentInformationId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Information Not Found"));

        paymentInformationMap.replace(paymentInformationId, newPaymentInformation);
        updateFile();
    }

    public void delete(UUID paymentInformationId) throws IOException, ResourceNotFoundException{
        PaymentInformation paymentInformation = find(paymentInformationId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Information Not Found"));
        paymentInformationMap.remove(paymentInformationId);
        updateFile();
    }

    public void deleteAll(UUID patientId) throws IOException{
        paymentInformationMap.entrySet()
                .removeIf(entry -> entry.getValue().getPatient().getUserID().equals(patientId));
        updateFile();
    }
}
