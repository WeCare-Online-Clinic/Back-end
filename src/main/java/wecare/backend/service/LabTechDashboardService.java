package wecare.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wecare.backend.model.dto.LabTechDataCard;
import wecare.backend.repository.ReportRepository;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LabTechDashboardService {

    @Autowired
    private ReportRepository reportRepo;

    //Lab technicians data cards
    public List<LabTechDataCard> getLabTechCardDetails() {

        List<LabTechDataCard> dataCards = new ArrayList<>();

        LocalDate toDay = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //convert the current date to a simple format

        //tests added today
        LabTechDataCard testsAddedTodayObject = new LabTechDataCard();
        Integer testCountAddedToday = reportRepo.getTestCountAddedToday(toDay);
        testsAddedTodayObject.setName("Tests Added Today");
        testsAddedTodayObject.setValue(testCountAddedToday);

        dataCards.add(testsAddedTodayObject);

        //lab report issued today
        LabTechDataCard reportsIssuedTodayObject = new LabTechDataCard();
        Integer reportsCountIssuedToday = reportRepo.getReportCountIssuedToday(toDay);
        reportsIssuedTodayObject.setName("Reports Issued Today");
        reportsIssuedTodayObject.setValue(reportsCountIssuedToday);

        dataCards.add(reportsIssuedTodayObject);

        //number of lab reports issued in this month
        LabTechDataCard reportsIssuedThisMonthObject = new LabTechDataCard();
        Month monthInName = toDay.getMonth();
        int month = toDay.getMonth().getValue();
        int year = toDay.getYear();
        Integer reportsCountIssuedThisMonth = reportRepo.getReportCountIssuedThisMonth(year, month);
        reportsIssuedThisMonthObject.setName("Lab Reports Issued in " + monthInName);
        reportsIssuedThisMonthObject.setValue(reportsCountIssuedThisMonth);

        dataCards.add(reportsIssuedThisMonthObject);


        //number of lab reports to be issued
        LabTechDataCard reportsToBeIssuedObject = new LabTechDataCard();
        Integer reportCountToBeIssued = reportRepo.getReportCountToBeIssued();
        reportsToBeIssuedObject.setName("Lab Reports To Be Issued");
        reportsToBeIssuedObject.setValue(reportCountToBeIssued);

        dataCards.add(reportsToBeIssuedObject);

        return dataCards;
    }

    //get monthly issued report count
    public ArrayList<Integer> getMonthlyIssuedReportsCount() {

        ArrayList<Integer> monthlyIssuedReportCountArray = new ArrayList<>();

        LocalDate toDay = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = toDay.getYear();

        for (int month = 1; month <= 12; month++) {
            Integer count = reportRepo.getMonthlyIssuedCount(year, month);
            monthlyIssuedReportCountArray.add(count);
        }
        return monthlyIssuedReportCountArray;
    }

    public ArrayList<Integer> getIssuedReportTypes() {
        ArrayList<Integer> IssuedReportTypeCountArray = new ArrayList<>();

        for(int type=1; type<=4; type++){
            Integer typeCount=reportRepo.getIssuedReportTypeCount(type);
            System.out.println("typeCount.....:"+typeCount);
            IssuedReportTypeCountArray.add(typeCount);
        }
        return IssuedReportTypeCountArray;

    }
}
