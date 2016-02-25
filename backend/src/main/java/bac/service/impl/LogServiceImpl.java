package bac.service.impl;

import bac.converter.LogConverter;
import bac.dto.DtoList;
import bac.dto.LogDto;
import bac.exception.ServiceException;
import bac.model.Log;
import bac.model.enums.ELogType;
import bac.repository.LogRepository;
import bac.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService{

    @Autowired
    private LogRepository logRepository;
    @Autowired
    private LogConverter logConverter;

    public LogServiceImpl() {
    }

    @Override
    public LogDto create(LogDto toCreate) throws ServiceException {
        if(toCreate == null)
            throw new IllegalArgumentException("Illegal Argument: Null");

        // validate
        // TODO : VALIDATION

        // convert
        Log log = logConverter.toEntity(toCreate);

        // create
        log = logRepository.save(log);

        // convert back and return
        return logConverter.toDto(log);
    }

    @Override
    public LogDto read(LogDto toRead) throws ServiceException {

        // validate

        // search
        Log log = logRepository.findOne(toRead.getId());

        // convert and return
        return logConverter.toDto(log);
    }

    @Override
    public LogDto update(LogDto toUpdate) throws ServiceException {

        // validate

        // convert
        Log oldLog = logConverter.toEntity(toUpdate);

        // update
        Log newLog = logRepository.save(oldLog);

        // convert and return
        return logConverter.toDto(newLog);
    }

    @Override
    public LogDto delete(LogDto toDelete) throws ServiceException {

        // validate

        // retrieve log
        Log log = logRepository.findOne(toDelete.getId());

        // delete log
        logRepository.delete(toDelete.getId());

        return logConverter.toDto(log);
    }


    @Override
    public DtoList<LogDto> readAllLogsPerObject(Long id, ELogType type) throws ServiceException {

        // validate

        //
        List<Log> result = logRepository.findByObjectIdAndType(id, type);

        // convert and return
        return logConverter.toDtoList(result);
    }
}
