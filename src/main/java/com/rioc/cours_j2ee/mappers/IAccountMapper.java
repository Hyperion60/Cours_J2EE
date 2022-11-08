package com.rioc.cours_j2ee.mappers;

import com.rioc.cours_j2ee.models.dao.Account;
import com.rioc.cours_j2ee.models.dto.AccountDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAccountMapper {
    AccountDto accountToAccountDto (Account account);
    Account accountDtoToAccount(AccountDto account);
}
