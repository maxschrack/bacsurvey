package bac.service;

import bac.dto.DtoList;
import bac.dto.LogDto;
import bac.exception.ServiceException;
import bac.model.enums.ELogType;
import org.springframework.stereotype.Service;

public interface LogService {

    LogDto create(LogDto toCreate) throws ServiceException;
    LogDto read(LogDto toRead) throws ServiceException;
    LogDto update(LogDto toUpdate) throws ServiceException;
    LogDto delete(LogDto toDelete) throws ServiceException;

    DtoList<LogDto> readAllLogsPerObject(Long id, ELogType types) throws ServiceException;
}
