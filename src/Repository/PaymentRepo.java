package Repository;

import Model.Class.MedicalRecord;
import Model.Class.Payment;
import Model.Class.PaymentInformation;
import Model.Enum.PaymentStatus;
import Exception.*;

import java.io.*;
import java.util.*;

public class PaymentRepo {

    private final PaymentInformationRepo paymentInformationRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final Scanner scanner;
    private Map<UUID, Payment> paymentMap;

    public PaymentRepo(PaymentInformationRepo paymentInformationRepo, MedicalRecordRepo medicalRecordRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.paymentInformationRepo = paymentInformationRepo;
        this.medicalRecordRepo = medicalRecordRepo;
        scanner = new Scanner(new File("src\\Text Files\\payment.txt"));
        paymentMap = new HashMap<>();

        readFile();
    }

    private void readFile() throws ResourceNotFoundException {
        while (scanner.hasNextLine()) {
            System.out.println("Reading Payment File.....");

            String[] line = scanner.nextLine().trim().split("\\|");
            String paymentId = line[1];
            String medicalRecordId = line[2];
            String paymentInformationId = line[3];
            String amount = line[4];
            String status = line[5];

            PaymentInformation paymentInformation = paymentInformationRepo.find(paymentInformationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Payment Information Not Found"));

            MedicalRecord medicalRecord = medicalRecordRepo.find(medicalRecordId)
                    .orElseThrow(() -> new ResourceNotFoundException("Medical Record Not Found"));

            Payment payment = new Payment(paymentId,medicalRecord, paymentInformation, amount, status);
            paymentMap.put(payment.getPaymentId(), payment);
        }
    }

    private void updateFile() throws IOException{
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("src\\Text Files\\payment.txt"));
        for(Payment payment : paymentMap.values()){
            fileWriter.write(payment.toString());
            fileWriter.newLine();
        }
        fileWriter.close();
    }

    public Map<UUID, Payment> getPaymentMap(){ return paymentMap;}

    public List<Payment> getPaymentList(){ return paymentMap.values().stream().toList();}

    public Optional<Payment> find(UUID paymentId){ return Optional.ofNullable(paymentMap.get(paymentId));}

    public Optional<Payment> find(String paymentId){ return Optional.ofNullable(paymentMap.get(UUID.fromString(paymentId)));}

    public void create(Payment payment) throws IOException{
        FileWriter fileWriter = new FileWriter("src\\Text Files\\payment.txt", true);
        fileWriter.write(payment.toString());
        fileWriter.write("\n");

        fileWriter.close();
        paymentMap.put(payment.getPaymentId(), payment);
        updateFile();
    }

    public void update(UUID paymentId, PaymentStatus status) throws ResourceNotFoundException, IOException{
        Payment payment = find(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Record Not Found"));

        payment.setStatus(status);
        updateFile();
    }

}
