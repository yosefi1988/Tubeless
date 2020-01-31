package ir.sajjadyosefi.android.xTubeless.classes.model.ServerConfig;

import java.io.Serializable;

/**
 * Created by sajjad on 10/31/2016.
 */
public class Version implements Serializable {

    String lastVersion ;
    String releaseDate ;
    boolean updateForce ;
    String versionWebPage ;
    String updateApi ;
    String newFeatures [];

    public String getLastVersion() {
        return lastVersion;
    }

    public void setLastVersion(String lastVersion) {
        this.lastVersion = lastVersion;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isUpdateForce() {
        return updateForce;
    }

    public void setUpdateForce(boolean updateForce) {
        this.updateForce = updateForce;
    }

    public String getVersionWebPage() {
        return versionWebPage;
    }

    public void setVersionWebPage(String versionWebPage) {
        this.versionWebPage = versionWebPage;
    }

    public String getUpdateApi() {
        return updateApi;
    }

    public void setUpdateApi(String updateApi) {
        this.updateApi = updateApi;
    }

    public String[] getNewFeatures() {
        return newFeatures;
    }

    public void setNewFeatures(String[] newFeatures) {
        this.newFeatures = newFeatures;
    }

}
