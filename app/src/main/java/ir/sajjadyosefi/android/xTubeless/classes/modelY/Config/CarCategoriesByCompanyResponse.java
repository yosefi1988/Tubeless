package ir.sajjadyosefi.android.xTubeless.classes.modelY.Config;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Company;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;


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

