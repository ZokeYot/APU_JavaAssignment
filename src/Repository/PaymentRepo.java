package Repository;

import Model.Class.MedicalRecord;
import Model.Class.Payment;
import Model.Class.PaymentMethod;
import Model.Enum.PaymentStatus;
import Exception.*;

import java.io.*;
import java.util.*;

public class PaymentRepo {

    private final PaymentMethodRepo paymentMethodRepo;
    private final MedicalRecordRepo medicalRecordRepo;
    private final Scanner scanner;
    private Map<UUID, Payment> paymentMap;

    public PaymentRepo(PaymentMethodRepo paymentMethodRepo, MedicalRecordRepo medicalRecordRepo) throws FileNotFoundException, ResourceNotFoundException {
        this.paymentMethodRepo = paymentMethodRepo;
        this.medicalRecordRepo = medicalRecordRepo;
        scanner = new Scanner(new File("src\\Text Files\\payment.txt"));
        paymentMap = new HashMap<>();

        readFile();
    }

    private void readFile() throws ResourceNotFoundException {
        System.out.println("Reading Payment File.....");
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().trim().split("\\|");
            String paymentId = line[0];
            String medicalRecordId = line[1];
            String paymentMethodId = line[2];
            String amount = line[3];
            String status = line[4];

            PaymentMethod paymentMethod = new PaymentMethod();

            if(!Objects.equals(paymentMethodId, "null"))
                paymentMethod = paymentMethodRepo.find(paymentMethodId)
                    .orElseThrow(() -> new ResourceNotFoundException("Payment Information Not Found"));

            MedicalRecord medicalRecord = medicalRecordRepo.find(medicalRecordId)
                    .orElseThrow(() -> new ResourceNotFoundException("Medical Record Not Found"));

            Payment payment = new Payment(paymentId,medicalRecord, paymentMethod, amount, status);
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

    public void update(Payment payment) throws ResourceNotFoundException, IOException{
        find(payment.getPaymentId())
                .orElseThrow(() -> new ResourceNotFoundException("Payment Record Not Found"));

        paymentMap.replace(payment.getPaymentId(), payment);
        updateFile();

    }

    public void updateStatus(UUID paymentId, PaymentStatus status) throws ResourceNotFoundException, IOException{
        Payment payment = find(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment Record Not Found"));

        payment.setStatus(status);
        updateFile();
    }

    public void delete(List<UUID> medicalRecordIDList) throws IOException {
        for(UUID medicalRecordID : medicalRecordIDList){
            for(Payment payment : paymentMap.values()){
                if(payment.getMedicalRecord().getMedicalRecordID().equals(medicalRecordID)){
                    paymentMap.remove(payment.getPaymentId());
                    break;
                }
            }
        }
    }
}
