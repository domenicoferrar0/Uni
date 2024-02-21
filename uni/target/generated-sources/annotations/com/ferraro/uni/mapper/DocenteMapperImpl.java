package com.ferraro.uni.mapper;

import com.ferraro.uni.dto.AnagraficaDTO;
import com.ferraro.uni.dto.CdlDTO;
import com.ferraro.uni.dto.DocenteDTO;
import com.ferraro.uni.entity.Anagrafica;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Docente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-21T10:14:36+0100",
    comments = "version: 1.6.0.Beta1, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class DocenteMapperImpl implements DocenteMapper {

    @Override
    public DocenteDTO docenteToDTO(Docente docente) {
        if ( docente == null ) {
            return null;
        }

        DocenteDTO docenteDTO = new DocenteDTO();

        docenteDTO.setAnagraficaDTO( anagraficaToAnagraficaDTO( docente.getAnagrafica() ) );
        docenteDTO.setCdlDTO( cdlToCdlDTO( docente.getCdl() ) );
        docenteDTO.setId( docente.getId() );
        docenteDTO.setCodiceDocente( docente.getCodiceDocente() );

        return docenteDTO;
    }

    protected AnagraficaDTO anagraficaToAnagraficaDTO(Anagrafica anagrafica) {
        if ( anagrafica == null ) {
            return null;
        }

        AnagraficaDTO anagraficaDTO = new AnagraficaDTO();

        anagraficaDTO.setId( anagrafica.getId() );
        anagraficaDTO.setNome( anagrafica.getNome() );
        anagraficaDTO.setCognome( anagrafica.getCognome() );
        anagraficaDTO.setNascita( anagrafica.getNascita() );
        anagraficaDTO.setCf( anagrafica.getCf() );
        anagraficaDTO.setIndirizzo( anagrafica.getIndirizzo() );
        anagraficaDTO.setGenere( anagrafica.getGenere() );
        anagraficaDTO.setLuogoDiNascita( anagrafica.getLuogoDiNascita() );

        return anagraficaDTO;
    }

    protected CdlDTO cdlToCdlDTO(Cdl cdl) {
        if ( cdl == null ) {
            return null;
        }

        CdlDTO cdlDTO = new CdlDTO();

        cdlDTO.setMaxStudenti( cdl.getMaxStudenti() );
        cdlDTO.setId( cdl.getId() );
        cdlDTO.setNome( cdl.getNome() );
        cdlDTO.setSettore( cdl.getSettore() );

        return cdlDTO;
    }
}
