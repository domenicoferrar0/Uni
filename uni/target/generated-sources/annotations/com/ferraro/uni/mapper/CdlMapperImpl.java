package com.ferraro.uni.mapper;

import com.ferraro.uni.dto.CdlDTO;
import com.ferraro.uni.dto.CdlDTOFull;
import com.ferraro.uni.dto.DocenteDTOList;
import com.ferraro.uni.dto.StudenteDTOList;
import com.ferraro.uni.entity.Cdl;
import com.ferraro.uni.entity.Docente;
import com.ferraro.uni.entity.Studente;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-21T11:08:44+0100",
    comments = "version: 1.6.0.Beta1, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class CdlMapperImpl implements CdlMapper {

    @Override
    public Cdl dtoToCdl(CdlDTO cdlDto) {
        if ( cdlDto == null ) {
            return null;
        }

        Cdl cdl = new Cdl();

        cdl.setId( cdlDto.getId() );
        cdl.setMaxStudenti( cdlDto.getMaxStudenti() );
        cdl.setNome( cdlDto.getNome() );
        cdl.setSettore( cdlDto.getSettore() );

        return cdl;
    }

    @Override
    public CdlDTOFull cdlToDtoFull(Cdl cdl) {
        if ( cdl == null ) {
            return null;
        }

        CdlDTOFull cdlDTOFull = new CdlDTOFull();

        cdlDTOFull.setDocenti( docenteSetToDocenteDTOListSet( cdl.getDocenti() ) );
        cdlDTOFull.setId( cdl.getId() );
        cdlDTOFull.setMaxStudenti( cdl.getMaxStudenti() );
        cdlDTOFull.setNome( cdl.getNome() );
        cdlDTOFull.setSettore( cdl.getSettore() );
        cdlDTOFull.setStudenti( studenteSetToStudenteDTOListSet( cdl.getStudenti() ) );

        return cdlDTOFull;
    }

    @Override
    public CdlDTO cdlToDto(Cdl cdl) {
        if ( cdl == null ) {
            return null;
        }

        CdlDTO cdlDTO = new CdlDTO();

        cdlDTO.setId( cdl.getId() );
        cdlDTO.setMaxStudenti( cdl.getMaxStudenti() );
        cdlDTO.setNome( cdl.getNome() );
        cdlDTO.setSettore( cdl.getSettore() );

        return cdlDTO;
    }

    protected Set<DocenteDTOList> docenteSetToDocenteDTOListSet(Set<Docente> set) {
        if ( set == null ) {
            return null;
        }

        Set<DocenteDTOList> set1 = new LinkedHashSet<DocenteDTOList>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Docente docente : set ) {
            set1.add( docenteToDTOList( docente ) );
        }

        return set1;
    }

    protected Set<StudenteDTOList> studenteSetToStudenteDTOListSet(Set<Studente> set) {
        if ( set == null ) {
            return null;
        }

        Set<StudenteDTOList> set1 = new LinkedHashSet<StudenteDTOList>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Studente studente : set ) {
            set1.add( studentToDTOList( studente ) );
        }

        return set1;
    }
}
