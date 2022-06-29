package com.yyd.yyd.constants.datasource;


import org.apache.shardingsphere.spi.masterslave.MasterSlaveLoadBalanceAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.Random;

public class MasterStragey implements MasterSlaveLoadBalanceAlgorithm {
    private static final Logger logger = LoggerFactory.getLogger(MasterStragey.class);
    @Override
    public String getDataSource(final String name, final String masterDataSourceName, final List<String> slaveDataSourceNames) {
        try {
            ReadSlave readSlave = HandleDataSource.getDataSource();
            logger.debug("MasterStragey getDataSource: get readSlave is {}",readSlave);
            if (readSlave == null) {
                return masterDataSourceName;
            }

            RunDatabaseType value = readSlave.value();
            logger.debug("MasterStragey getDataSource: get readSlave value is {}",value);
            if (value.equals(RunDatabaseType.MASTER)) {
                logger.debug("MasterStragey getDataSource:  return is {}",masterDataSourceName);
                return masterDataSourceName;
            } else if (value.equals(RunDatabaseType.RADOMSlAVE)) {
                String randomSlaveName = slaveDataSourceNames.get(new Random().nextInt(slaveDataSourceNames.size()));
                logger.debug("MasterStragey getDataSource:  return is {}",randomSlaveName);
                return randomSlaveName;
            } else if (value.equals(RunDatabaseType.ASSIGNSlAVE)) {
                int slaveNum = readSlave.slaveNum();
                int size = slaveDataSourceNames.size();
                logger.debug("MasterStragey getDataSource: get readSlave slaveNum is {} and slaveDataSourceNames size is {}",slaveNum,size);
                if(slaveNum<0||slaveNum>=size){
                    logger.debug("MasterStragey getDataSource:  return is {}",masterDataSourceName);
                    return  masterDataSourceName;
                }else{
                    logger.debug("MasterStragey getDataSource:  return is {}",slaveDataSourceNames.get(slaveNum));
                    return slaveDataSourceNames.get(slaveNum);
                }
            }else{
                logger.debug("MasterStragey getDataSource:  return is {}",masterDataSourceName);
                return masterDataSourceName;
            }
        }catch (Exception e){
            logger.warn("MasterStragey getDataSource: hava exception  is {} ",e.getMessage());
        }

        logger.debug("MasterStragey getDataSource: finally return master");
        return masterDataSourceName;

    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public Properties getProperties() {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
