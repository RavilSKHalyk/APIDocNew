package APIDocNew.controller;

import APIDocNew.s3.S3Util;
import APIDocNew.soap.RequestGBD;
import APIDocNew.util.MyUtil;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping("/PersonDetailsController")
public class PersonDetailsController {
    /**
     * возвращает документ по ИИН в формате base64 из хранилища
     * если в хранилище нет или не актуально, отправляет смс на номер из базы мобильных граждан
     * @param iin ИИН
     * @return ответ из ГБД
     * @throws Exception исключение
     */
    @GetMapping("/scan/{iin}/{docType}")
    public String getPersonDetailsFromS3 (@Parameter(description = "ИИН 12 цифр", example = "880912300763") @PathVariable String iin,
                                          @Parameter(description = "IdentityCard - Удостоверение личности<br>" +
                                                  "DriverLicense - Водительское удостоверение<br>" +
                                                  "BirthCertificate - Свидетельство о рождении<br>" +
                                                  "MarriageCertificate - Свидетельство о заключении брака<br>" +
                                                  "ChangeFioCertificate - Свидетельство о перемене фамилии, имени, отчества<br>" +
                                                  "DivorceCertificate - Свидетельство о расторжении брака<br>" +
                                                  "TechPassport - Технический паспорт<br>" +
                                                  "PcrCertificate - Результат ПЦР тестирования на COVID-19<br>" +
                                                  "StudentCard - Студенческий билет<br>" +
                                                  "Diploma - Диплом<br>" +
                                                  "Pension - Удостоверение пенсионера<br>" +
                                                  "Oralman - Удостоверение кандаса<br>" +
                                                  "SRTS - Свидетельство о регистрации транспортного средства<br>" +
                                                  "SocialId - Социальный ID<br>" +
                                                  "Vaccination - Паспорт вакцинации<br>" +
                                                  "DisabilityCertificate - Удостоверение лица с инвалидностью<br>" +
                                                  "LargeFamilyCertificate - Удостоверение о многодетной семье<br>" +
                                                  "AspCertificate - Удостоверение получателя АСП<br>", example = "IdentityCard")
                                          @PathVariable String docType) throws Exception {
        //проверяем ИИН
        if (!new MyUtil().isNumber(iin)){
            throw new Exception("ИИН содержит не числовые символы");
        }
        if (iin.length()!=12){
            throw new Exception("не правильное количество символов ИИН");
        }
        if (docType==null || docType.equals("")){
            throw new Exception("незаполнено поле docType");
        }
        if (!docType.equals("IdentityCard") &&
                !docType.equals("DriverLicense") &&
                !docType.equals("BirthCertificate") &&
                !docType.equals("MarriageCertificate") &&
                !docType.equals("ChangeFioCertificate") &&
                !docType.equals("DivorceCertificate") &&
                !docType.equals("TechPassport") &&
                !docType.equals("PcrCertificate") &&
                !docType.equals("StudentCard") &&
                !docType.equals("Diploma") &&
                !docType.equals("Pension") &&
                !docType.equals("Oralman") &&
                !docType.equals("SRTS") &&
                !docType.equals("SocialId") &&
                !docType.equals("Vaccination") &&
                !docType.equals("DisabilityCertificate") &&
                !docType.equals("LargeFamilyCertificate") &&
                !docType.equals("AspCertificate")){
            throw new Exception("некорректно заполнено поле docType");
        }
        //проверяем существование записи в хранилище, предположительно храним обьект с названием иин
        S3Util s3Util = new S3Util();
        String bucketName = "tests3";
        String udvBase64 = "Отправлено смс";
        if (s3Util.isExsist(bucketName, iin + docType)){
            //System.out.println("есть такой обьект");
            //проверяем актуальность данных
            udvBase64 = s3Util.getStringFromObject(bucketName, iin + docType);
            if(udvBase64!=null && !udvBase64.equals("")){ //нужно добавить проверку на актуальность
                //возвращаем результат, сейчас это удостоверение в формате base64
                return udvBase64;
            }
        }
        // если данных нет или они не актуальны делаем запрос в ГБД
        RequestGBD requestGBD = new RequestGBD();
        // база мобильных граждан
        requestGBD.getOnlineAccessBMG(iin);
        // согласие на запрос
        requestGBD.getOnlineAccess(iin, docType);

        return udvBase64;
    }

    /**
     * возвращает документ по ИИН в формате base64 из ГБД (сервис «Цифровые документы»)
     * @param iin ИИН
     * @param smsCod смс код
     * @return документ в формате base64
     * @throws Exception исключение
     */
    @GetMapping("/scan/{iin}/{docType}/{smsCod}")
    public String getPersonDetailsFromGBD (@Parameter(description = "ИИН 12 цифр", example = "880912300763") @PathVariable String iin,
                                           @PathVariable String smsCod,
                                           @Parameter(description = "IdentityCard - Удостоверение личности<br>" +
                                                   "DriverLicense - Водительское удостоверение<br>" +
                                                   "BirthCertificate - Свидетельство о рождении<br>" +
                                                   "MarriageCertificate - Свидетельство о заключении брака<br>" +
                                                   "ChangeFioCertificate - Свидетельство о перемене фамилии, имени, отчества<br>" +
                                                   "DivorceCertificate - Свидетельство о расторжении брака<br>" +
                                                   "TechPassport - Технический паспорт<br>" +
                                                   "PcrCertificate - Результат ПЦР тестирования на COVID-19<br>" +
                                                   "StudentCard - Студенческий билет<br>" +
                                                   "Diploma - Диплом<br>" +
                                                   "Pension - Удостоверение пенсионера<br>" +
                                                   "Oralman - Удостоверение кандаса<br>" +
                                                   "SRTS - Свидетельство о регистрации транспортного средства<br>" +
                                                   "SocialId - Социальный ID<br>" +
                                                   "Vaccination - Паспорт вакцинации<br>" +
                                                   "DisabilityCertificate - Удостоверение лица с инвалидностью<br>" +
                                                   "LargeFamilyCertificate - Удостоверение о многодетной семье<br>" +
                                                   "AspCertificate - Удостоверение получателя АСП<br>", example = "IdentityCard") @PathVariable String docType) throws Exception {
        //проверяем ИИН
        if (!new MyUtil().isNumber(iin)) {
            throw new Exception("ИИН содержит не числовые символы");
        }
        if (iin.length() != 12) {
            throw new Exception("не правильное количество символов ИИН");
        }
        if (smsCod.equals("") || smsCod==null){
            throw new Exception("Смс код пустой");
        }
        RequestGBD requestGBD = new RequestGBD();
        String udvBase64 = requestGBD.getDigitalDocuments(iin, smsCod, docType);

        //после запроса в ГБД обновляются данные в хранилище
        S3Util s3Util = new S3Util();
        String bucketName = "tests3";
        if (s3Util.isExsist(bucketName, iin + docType)){
            //System.out.println("удаляем старый обьект");
            s3Util.DeleteObjectFromBucket(bucketName, iin + docType);
        }
        s3Util.PutObjectInBucket(bucketName, iin+docType, createSampleFile(udvBase64));
        //System.out.println("записали обьект с новыми данными");
        return udvBase64;
    }

    /**
     * создание временного файла для записи в хранилище
     * @param data текст файла
     * @return файл
     * @throws IOException ошибка
     */
    private static File createSampleFile(String data) throws IOException {
        File file = File.createTempFile("aws-java-sdk-", ".txt");
        file.deleteOnExit();
        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(data);
        writer.close();
        return file;
    }

    /**
     * обработчик ошибок
     * @param exception исключение
     * @return код + текст ошибки
     */
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        return ResponseEntity
                .status(500)
                .body(new ErrorMessage(exception.getMessage()));
    }*/

}
