package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;

public class Configuration extends ServerResponseStatus {

    ConfigurationObject configurationB = new ConfigurationObject();

    public ConfigurationObject getConfiguration() {
        return configurationB;
    }

    public void setConfiguration(ConfigurationObject configuration) {
        this.configurationB = configuration;
    }

}
