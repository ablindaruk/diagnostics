package com.medical.hospital.diagnostics.medicineresources.mappers;

import com.medical.hospital.diagnostics.medicineresources.dto.StockDTO;
import com.medical.hospital.diagnostics.medicineresources.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);
    StockDTO stockToStockDTO(Stock stock);
    Stock dtoToStock(StockDTO stockDTO);
    List<StockDTO> stockListToDTOlist(List<Stock> stockList);
}
