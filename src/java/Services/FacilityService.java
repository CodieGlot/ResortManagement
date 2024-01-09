package Services;

import java.sql.ResultSet;
import java.sql.SQLException;

import Repositories.Common.RepositoryBase;
import Repositories.Entities.FacilityEntity;

public class FacilityService extends RepositoryBase<FacilityEntity> {

    @Override
    protected String getTableName() {
        return "Facilities";
    }

    @Override
    protected FacilityEntity createEntityFromResultSet(ResultSet rs) throws SQLException {
        return new FacilityEntity(rs);
    }

}
