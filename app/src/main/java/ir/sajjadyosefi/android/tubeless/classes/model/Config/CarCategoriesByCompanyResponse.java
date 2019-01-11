package ir.sajjadyosefi.android.tubeless.classes.model.Config;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.Company;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.ServerResponseBase;

/**
 * Created by sajjad on 3/2/2017.
 */
public class CarCategoriesByCompanyResponse extends ServerResponseBase {

    private List<Company> companyList ;

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }
}

