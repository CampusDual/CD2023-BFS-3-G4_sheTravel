package com.campusdual.viajerasapp.model.core.dao;

import com.ontimize.jee.server.dao.common.ConfigurationFile;
import com.ontimize.jee.server.dao.jdbc.OntimizeJdbcDaoSupport;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

@Lazy
@Repository(value = "ActivityDao")
@ConfigurationFile(
        configurationFile = "dao/ActivityDao.xml",
        configurationFilePlaceholder = "dao/placeholders.properties")
public class ActivityDao extends OntimizeJdbcDaoSupport {

    public static final String ACTIVITY_NAME = "activity_name";
    public static final String ID_ACTIVITY = "id_activity";


}
