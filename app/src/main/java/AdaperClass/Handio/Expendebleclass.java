package AdaperClass.Handio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Expendebleclass {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> MyAccount = new ArrayList<String>();
        MyAccount.add("Favourites");
        MyAccount.add("Setting");

        List<String> Payment = new ArrayList<String>();
        Payment.add("Refund Status");
        Payment.add("Payment Mode");

//        List<String> basketball = new ArrayList<String>();
//        basketball.add("United States");
//        basketball.add("Spain");
//        basketball.add("Argentina");
//        basketball.add("France");
//        basketball.add("Russia");

        expandableListDetail.put("My Account", MyAccount);
        expandableListDetail.put("Payment & Refunds", Payment);

//        expandableListDetail.put("BASKETBALL TEAMS", basketball);
        return expandableListDetail;
    }
}
