package ma.ehtp.gestionrisqueit.phase5.services;

import ma.ehtp.gestionrisqueit.phase0.modelles.Organization;
import ma.ehtp.gestionrisqueit.phase0.tools.U;
import ma.ehtp.gestionrisqueit.phase3.modelles.RiskMapping;
import ma.ehtp.gestionrisqueit.phase3.services.RiskMappingService;
import ma.ehtp.gestionrisqueit.phase5.modelles.KriMeasures;
import ma.ehtp.gestionrisqueit.phase5.modelles.dto.KriMeasuresDTO;
import ma.ehtp.gestionrisqueit.phase5.repositories.KriMeasuresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KriMeasuresServiceImp implements KriMeasuresService{
    @Autowired
    KriMeasuresRepository kriMeasuresRepository;

    @Autowired
    RiskMappingService riskMappingService;

    @Override
    public KriMeasures findByRiskMapping(RiskMapping riskMapping) {
        return kriMeasuresRepository.findByRiskMapping(riskMapping);
    }

    @Override
    public KriMeasures save(KriMeasuresDTO kriMeasuresDTO) {
        Long id = kriMeasuresDTO.getId();
        KriMeasures kms = new KriMeasures();
        if (id != null){
            kriMeasuresRepository.findById(id).ifPresent(km -> {
                km.setMesure1(kriMeasuresDTO.getMesure1());
                km.setCible1(kriMeasuresDTO.getCible1());
                km.setEcart1(kriMeasuresDTO.getEcart1());
                km.setMesure2(kriMeasuresDTO.getMesure2());
                km.setCible2(kriMeasuresDTO.getCible2());
                km.setEcart2(kriMeasuresDTO.getEcart2());
                km.setMesure3(kriMeasuresDTO.getMesure3());
                km.setCible3(kriMeasuresDTO.getCible3());
                km.setEcart3(kriMeasuresDTO.getEcart3());
                km.setMesure4(kriMeasuresDTO.getMesure4());
                km.setCible4(kriMeasuresDTO.getCible4());
                km.setEcart4(kriMeasuresDTO.getEcart4());
                km.setExplicationsDesEcarts(kriMeasuresDTO.getExplicationsDesEcarts());
                km.setMesuresCorrectives(kriMeasuresDTO.getMesuresCorrectives());
                 kriMeasuresRepository.save(km);
            });
            return new KriMeasures();
        }else{

            riskMappingService.findById(kriMeasuresDTO.getRmId()).ifPresent(riskMapping ->{

                kms.setMesure1(kriMeasuresDTO.getMesure1());
                kms.setCible1(kriMeasuresDTO.getCible1());
                kms.setEcart1(kriMeasuresDTO.getEcart1());
                kms.setMesure2(kriMeasuresDTO.getMesure2());
                kms.setCible2(kriMeasuresDTO.getCible2());
                kms.setEcart2(kriMeasuresDTO.getEcart2());
                kms.setMesure3(kriMeasuresDTO.getMesure3());
                kms.setCible3(kriMeasuresDTO.getCible3());
                kms.setEcart3(kriMeasuresDTO.getEcart3());
                kms.setMesure4(kriMeasuresDTO.getMesure4());
                kms.setCible4(kriMeasuresDTO.getCible4());
                kms.setEcart4(kriMeasuresDTO.getEcart4());
                kms.setExplicationsDesEcarts(kriMeasuresDTO.getExplicationsDesEcarts());
                kms.setMesuresCorrectives(kriMeasuresDTO.getMesuresCorrectives());
                kms.setRiskMapping(riskMapping);
            } );
            KriMeasures kriMeasures = kriMeasuresRepository.save(kms);
            riskMappingService.findById(kms.getRiskMapping().getId()).ifPresent(riskMapping -> {
                riskMapping.setKriMeasures(kriMeasures);
                riskMapping.setSousProcessusList(new ArrayList<>());
                riskMappingService.save(riskMapping);
            });


            return kriMeasures ;

        }


    }

    @Override
    public List<KriMeasures> saveAll(List<KriMeasuresDTO> kriMeasuresDTOList) {
        List<KriMeasures> kriMeasuresList = new ArrayList<>();
        for (KriMeasuresDTO kmdto:kriMeasuresDTOList) {
            kriMeasuresList.add(save(kmdto));
        }
        return kriMeasuresList;
    }

    @Override
    public List<KriMeasuresDTO> findByOrganization(Organization organization) {
        List<KriMeasuresDTO> kriMeasuresDTOList = new ArrayList<>();
        List<RiskMapping> riskMappingList = riskMappingService.findByOrganizationWithoutSP(organization);
        U.ptxt("riskMappingList lenght :"+riskMappingList.size());
        for (RiskMapping rm:riskMappingList) {
            if (rm.getKriMeasures() == null){
                U.ptxt("rm.getKriMeasures == null");
                rm.setKriMeasures(new KriMeasures());
            }

            KriMeasures rmkm = rm.getKriMeasures();
            KriMeasuresDTO kmdto = new KriMeasuresDTO();
            kmdto.setMesure1(rmkm.getMesure1());
            kmdto.setCible1(rmkm.getCible1());
            kmdto.setEcart1(rmkm.getEcart1());
            kmdto.setMesure2(rmkm.getMesure2());
            kmdto.setCible2(rmkm.getCible2());
            kmdto.setEcart2(rmkm.getEcart2());
            kmdto.setMesure3(rmkm.getMesure3());
            kmdto.setCible3(rmkm.getCible3());
            kmdto.setEcart3(rmkm.getEcart3());
            kmdto.setMesure4(rmkm.getMesure4());
            kmdto.setCible4(rmkm.getCible4());
            kmdto.setEcart4(rmkm.getEcart4());

            if (rmkm.getExplicationsDesEcarts() != null)
            kmdto.setExplicationsDesEcarts(rmkm.getExplicationsDesEcarts());

            if (rmkm.getMesuresCorrectives() != null)
            kmdto.setMesuresCorrectives(rmkm.getMesuresCorrectives());

            kmdto.setId(rmkm.getId());
            kmdto.setKRI(rm.getKRI());
            kmdto.setRmId(rm.getId());
            kmdto.setRisque(rm.getRisque());


            kriMeasuresDTOList.add(kmdto);
        }



        return kriMeasuresDTOList;
    }


}
