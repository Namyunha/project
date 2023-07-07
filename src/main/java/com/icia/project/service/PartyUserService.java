package com.icia.project.service;

import com.icia.project.Entity.PartyUserEntity;
import com.icia.project.dto.PartyUserDTO;
import com.icia.project.repository.PartyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartyUserService {
    private final PartyUserRepository partyUserRepository;

    public void save(PartyUserDTO partyUserDTO) {
        System.out.println("서비스에 있는 partyUserDTO = " + partyUserDTO);
        PartyUserEntity partyUserEntity = PartyUserEntity.toSaveEntity(partyUserDTO);
        partyUserRepository.save(partyUserEntity);
    }
}
