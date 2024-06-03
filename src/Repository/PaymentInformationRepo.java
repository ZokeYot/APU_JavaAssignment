package Repository;

import Model.Class.Patient;
import Model.Class.PaymentInformation;
import Exception.*;

import java.io.*;
import java.util.*;

public class PaymentInformationRepo {

    private final Map<UUID, PaymentInformation> paymentInformationMap;
    private final Map<UUID, List<PaymentInformation>> patientPaymentInformationMap;
    private final PatientRepo patientRepo;
    private final Scanner scanner;

    public PaymentInformationRepo(PatientRepo patientRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.patientRepo = patientRepo;
        paymentInformationMap = new HashMap<>();
        patientPaymentInformationMap = new HashMap<>();
        scanner = new Scanner(new File("src\\Text Files\\payment information.txt"));
    }

    private void readFile() throws ResourceNotFoundException{
        while(scanner.hasNextLine()){
            System.out.println("Reading Medical Record File.....");
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
        for(PaymentInformation paymentInformation : paymentInformationMap.values()){
            UUID patientId = paymentInformation.getPatient().getUserID();
            patientPaymentInformationMap
                    .computeIfAbsent(patientId, k -> new ArrayList<>())
                    .add(paymentInformation);
        }
    }

    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Text Files\\patient.txt"));
        for(PaymentInformation paymentInformation : paymentInformationMap.values()){
            fileWriter.write(paymentInformation.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, PaymentInformation> getPaymentInformationMap(){ return paymentInformationMap;}

    public List<PaymentInformation> getPaymentInformationList(){ return paymentInformationMap.values().stream().toList();}

    public List<PaymentInformation> getPatientPaymentInformation(Patient patient){ return patientPaymentInformationMap.get(patient.getUserID()); }

    public Optional<PaymentInformation> find(UUID paymentInformationId){ return Optional.ofNullable(paymentInformationMap.get(paymentInformationId));}

    public Optional<PaymentInformation> find(String paymentInformationId){ return Optional.ofNullable(paymentInformationMap.get(UUID.fromString(paymentInformationId)));}

    public void create(PaymentInformation paymentInformation) throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("Text Files\\patient.txt" , true));

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

    public void delete(UUID paymentInformationId) throws IOException{
        paymentInformationMap.remove(paymentInformationId);
        updateFile();
    }

    public void deleteAll(UUID patientId) throws IOException{
        patientPaymentInformationMap.remove(patientId);
        paymentInformationMap.entrySet()
                .removeIf(entry -> entry.getValue().getPatient().getUserID().equals(patientId));
        updateFile();
    }
}
