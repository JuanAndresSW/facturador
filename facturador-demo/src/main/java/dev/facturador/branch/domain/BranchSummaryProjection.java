package dev.facturador.branch.domain;

import java.util.List;

public interface BranchSummaryProjection {
    Long getBranchId();

    String getFantasyName();

    String getLocality();

    String getStreet();

    String getAddressNumber();

    List<PointOfSaleSummaryProjection> getPointsOfSale();


}
