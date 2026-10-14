import csv_classes.CommercialProxy;
import csv_classes.Distance;
import csv_classes.PropertyAssessment;
import csv_classes.PropertyAssessments;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/*
 * Are residential properties closer to commercial properties assessed higher or lower
 * than comparable homes that are further away?
 * Target: developers, homebuyers
 * Expected Benefit: shows whether home value is higher or lower with commercial development nearby
 */
public class IndividualProject {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.print("CSV filename: ");
        String csv = scanner.nextLine();
        CommercialProxy api;
        try{
            api =  new CommercialProxy(csv);
        } catch (IOException e){
            System.err.println("Error: can't open file " + csv);
            return;
        }

        System.out.println("\n DATA COLLECTED");
        System.out.print(api.getDatasetSummary());

        System.out.println("\n MEDIAN VALUE BY DISTANCE TO NEAREST COMMERCIAL PROPERTY");
        System.out.printf("%-18s %10s %12s%n", "Distance", "Home", "Median");
        for (Map.Entry<Distance, PropertyAssessments> entry : api.getHomeByDistanceBand().entrySet()) {
            System.out.printf("%-18s %,10d $%,11d%n", entry.getKey(), entry.getValue().size(), entry.getValue().median());
        }

        System.out.println("\n NEAR VS FAR COMPARABLE HOMES");
        System.out.println("Near - within " + api.getNearThresholdMeters() + " m of a commercial property.");
        System.out.println("Compared inside " + api.getGroupsCompared() + " groups of same-neighbourhood same-type (house or condo)");
        System.out.printf("Median difference in value, near vs far: %+.1f%%%n", api.getNearFarDistance());
    }
}
