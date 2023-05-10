package ma.ehtp.gestionrisqueit.phase3.repositories;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.modelles.dto.NbrOfRMByCat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RiskMappingRepository extends JpaRepository<RiskMapping , Long> {

    public List<RiskMapping> findByOrganization(Organization organization);
    public List<RiskMapping> findByOrganizationAndIsHidden(Organization organization , Boolean isHidden);


    @Query(
            value = "SELECT category , COUNT(id) as nrbrm FROM (select * from risk_mapping WHERE organization_id = :orgid) as allrm GROUP BY category;",
            nativeQuery = true)
    List<Object[]> countAllByCategory(Long orgid);

    @Query(
            value = "SELECT frequence , impact , COUNT(id) as nbr FROM (select * from risk_mapping WHERE organization_id = :orgid) as allrm GROUP BY frequence , impact;",
            nativeQuery = true)
    List<Object[]> countAllByFrequenceAndImpact(Long orgid);

    @Query(
            value = "SELECT * FROM `risk_mapping` WHERE organization_id = :orgid ORDER BY CASE\n" +
                    "WHEN criticality_level = 'Critical' THEN 1\n" +
                    "WHEN criticality_level = 'Major' THEN 2\n" +
                    "WHEN criticality_level = 'Moderate' THEN 3\n" +
                    "WHEN criticality_level = 'Minor' THEN 4\n" +
                    "ELSE 5 END\n" +
                    "LIMIT 10;",
            nativeQuery = true)
    List<RiskMapping> findByOrganizationOrderByCriticalityLevel(Long orgid);

    List<RiskMapping> findByOrganizationOrderByExpositionDesc(Organization organization);

    @Query(
            value = "SELECT efficacite , COUNT(id) as nbr FROM (select * from risk_mapping WHERE organization_id = :orgid) as allrm  GROUP BY efficacite  ORDER BY efficacite;",
            nativeQuery = true)
    List<Object[]> countAllByEfficacite(Long orgid);

    @Query(
            value = "SELECT * FROM risk_mapping WHERE efficacite IN (\"1\",\"2\") AND criticality_level  IN (\"Critical\",\"Major\") AND organization_id = :orgid",
            nativeQuery = true
    )
    List<RiskMapping> zoomSurLesRisque(Long orgid);

    public List<RiskMapping> findByOrganizationAndIsHiddenAndCriticalityLevel(Organization organization , Boolean isHidden ,String criticalityLevel );

    List<RiskMapping> findByOrganizationAndIsHiddenAndCriticalityLevel(Organization organization, boolean b, String s);
}
