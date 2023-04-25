package com.graduate.touslestemp.service.impl;

import com.graduate.touslestemp.domain.dto.PageResponseDTO;
import com.graduate.touslestemp.domain.dto.TimeKeepingDTO;
import com.graduate.touslestemp.domain.entity.Staff;
import com.graduate.touslestemp.domain.entity.Store;
import com.graduate.touslestemp.domain.entity.TimeKeeping;
import com.graduate.touslestemp.domain.mapper.TimeKeepingMapper;
import com.graduate.touslestemp.domain.repository.TimeKeepingRepository;
import com.graduate.touslestemp.exception.RequestException;
import com.graduate.touslestemp.service.TimeKeepingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeKeepingServiceImpl implements TimeKeepingService {
    @Autowired
    TimeKeepingRepository timeKeepingRepository;
    @Autowired
    TimeKeepingMapper timeKeepingMapper;

    @Override
    public PageResponseDTO<?> getAllTimeKeeping(Pageable request) {
        return null;
    }

    @Override
    public TimeKeeping find(Long id) {
        Optional<TimeKeeping> timeKeeping = timeKeepingRepository.findById(id);
        if (timeKeeping.isEmpty()) {
            throw new RequestException("Not found this time keeping, id: " + id);
        }
        return timeKeepingRepository.findById(id).get();
    }

    @Override
    public TimeKeepingDTO create(TimeKeeping timeKeeping) throws Exception {
        return (timeKeepingMapper.toTimeKeepingDTO(timeKeepingRepository.save(timeKeeping)));
    }

    @Override
    public void delete(Long id) {
        timeKeepingRepository.delete(timeKeepingRepository.findById(id)
                .orElseThrow(()->new RequestException("Not found this time keeping: "+id)));
    }

    @Override
    public TimeKeepingDTO update(TimeKeepingDTO timeKeepingDTO, Long id) throws Exception {
        TimeKeeping local = this.timeKeepingRepository.findById(id)
                .orElseThrow(() -> new RequestException("Can't found this time keeping id: " + id));
        timeKeepingMapper.updateEntity(timeKeepingDTO, local);

        return (timeKeepingMapper.toTimeKeepingDTO(timeKeepingRepository.save(local)));
    }

    @Override
    public List<TimeKeepingDTO> search(String keyword) {
        return null;
    }

    @Override
    public List<TimeKeepingDTO> filter(Long id) {
        return null;
    }
}
