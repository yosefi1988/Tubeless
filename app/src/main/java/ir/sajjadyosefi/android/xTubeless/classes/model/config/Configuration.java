package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.ConfigurationObject;

public class Configuration extends ServerResponseStatus {

//    ConfigurationObject configurationB = new ConfigurationObject();
    ConfigurationObject configuratioSokht = new ConfigurationObject();

    public ConfigurationObject getConfiguration() {
        return configuratioSokht;
    }

    public void setConfiguration(ConfigurationObject configuration) {
        this.configuratioSokht = configuration;
    }

}
