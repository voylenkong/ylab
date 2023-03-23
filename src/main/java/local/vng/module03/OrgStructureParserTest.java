package local.vng.module03;

import local.vng.module03.orgstructure.OrgStructureParser;
import local.vng.module03.orgstructure.OrgStructureParserImpl;
import local.vng.module03.orgstructure.model.Employee;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * OrgStructureParserTest
 * Загрузка структуры организации из CSV файла.
 *
 * @author VoylenkoNG
 * 18.03.2023
 */
public class OrgStructureParserTest {
    public static void main(String[] args) {

        OrgStructureParser orgStructureParser = new OrgStructureParserImpl();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Путь к файлу со структурой организации *.csv: ");
        String filePath = scanner.next();

        File inputFile = new File(filePath);

        try {
            Employee boss = orgStructureParser.parseStructure(inputFile);
            System.out.print(boss);
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        scanner.close();
    }
}
