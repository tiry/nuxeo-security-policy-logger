package org.nuxeo.security.logger;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.security.ACP;
import org.nuxeo.ecm.core.api.security.Access;
import org.nuxeo.ecm.core.model.Document;
import org.nuxeo.ecm.core.query.sql.model.SQLQuery.Transformer;
import org.nuxeo.ecm.core.security.AbstractSecurityPolicy;
import org.nuxeo.ecm.core.security.SecurityPolicy;

public class LogingSecurityPolicy extends AbstractSecurityPolicy implements SecurityPolicy {

    protected static final Log log = LogFactory.getLog(LogingSecurityPolicy.class);

    @Override
    public Transformer getQueryTransformer(String repositoryName) {
        return Transformer.IDENTITY;
    }

    @Override
    public QueryTransformer getQueryTransformer(String repositoryName, String queryLanguage) {
        return  QueryTransformer.IDENTITY;
    }

    @Override
    public boolean isExpressibleInQuery(String repositoryName) {
        return true;
    }

    @Override
    public Access checkPermission(Document doc, ACP mergedAcp, Principal principal, String permission,
            String[] resolvedPermissions, String[] additionalPrincipals) {

        StringBuffer sb = new StringBuffer();

        sb.append("target=");
        try {
            sb.append(doc.getPath().toString());
        } catch (Exception e) {
            sb.append(doc.getUUID());
        }
        sb.append(";checked permissions= ");
        sb.append(permission);
        sb.append(";principal= ");
        sb.append(principal.getName());

        sb.append("\n");
        sb.append("  resolvedPermissions= [");
        for (String p : resolvedPermissions) {
            sb.append(p);
            sb.append(",");
        }
        sb.append("]");


        sb.append("\n");
        sb.append("  groups= [");
        for (String p : additionalPrincipals) {
            sb.append(p);
            sb.append(",");
        }
        sb.append("]");

        log.info("Permission check :" + sb.toString());

        return Access.UNKNOWN;
    }

}
