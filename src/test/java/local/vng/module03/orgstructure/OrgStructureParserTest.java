package local.vng.module03.orgstructure;

import local.vng.module03.orgstructure.model.Employee;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * OrgStructureParserTest
 *
 * @author VoylenkoNG
 * 19.03.2023
 */
public class OrgStructureParserTest {

    @Test
    public void testOrgStructure() {
        OrgStructureParser orgStructureParser = new OrgStructureParserImpl();
        File inputFile = new File("src\\main\\resources\\orgstruct2.csv");

        try {
            Employee boss = orgStructureParser.parseStructure(inputFile);
            String bossName = "Иван Иванович";
            String bossPosition = "Генеральный директор";

            String leadAccName = "Крокодилова Людмила Петровна";
            String leadAccPosition = "Главный бухгалтер";

            List<Employee> sub = boss.getSubordinate();
            var leadAccEmpl = new Employee();
            for (Employee e : sub) {
                if (e.getName().equals(leadAccName) & e.getPosition().equals(leadAccPosition)) {
                    leadAccEmpl = e;
                }
            }

            Employee finalLeadAccEmpl = leadAccEmpl;
            assertAll(
                    () -> assertEquals(bossName, boss.getName()),
                    () -> assertEquals(bossPosition, boss.getPosition()),
                    () -> assertEquals(leadAccName, finalLeadAccEmpl.getName()),
                    () -> assertEquals(leadAccPosition, finalLeadAccEmpl.getPosition())
            );

        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }
    }
}
