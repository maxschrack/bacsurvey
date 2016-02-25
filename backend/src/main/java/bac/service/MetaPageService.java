package bac.service;

import bac.dto.EntityDto;
import bac.dto.MetaPageDto;
import bac.dto.QuestionnaireDto;
import bac.exception.ServiceException;

public interface MetaPageService {
    MetaPageDto createStartPage(MetaPageDto toCreate) throws ServiceException;
    MetaPageDto createEndPage(MetaPageDto toCreate) throws ServiceException;

    MetaPageDto readStartPagePerQuestionnaire(QuestionnaireDto toRead) throws ServiceException;
    MetaPageDto readEndPagePerQuestionnaire(QuestionnaireDto toRead) throws ServiceException;

    MetaPageDto updateMetaPage(MetaPageDto toUpdate) throws ServiceException;

    MetaPageDto deleteMetaPage(MetaPageDto toDelete) throws ServiceException;


}
