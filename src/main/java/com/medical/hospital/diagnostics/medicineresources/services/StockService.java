package com.medical.hospital.diagnostics.medicineresources.services;

import com.medical.hospital.diagnostics.exceptions.StockNotFoundException;
import com.medical.hospital.diagnostics.interfaces.StockRepository;
import com.medical.hospital.diagnostics.interfaces.StockServiceInterface;
import com.medical.hospital.diagnostics.medicineresources.dto.StockDTO;
import com.medical.hospital.diagnostics.medicineresources.entity.Stock;
import com.medical.hospital.diagnostics.medicineresources.mappers.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService implements StockServiceInterface {
    @Autowired
    private StockRepository stockRepository;
    private final StockMapper stockMapper;
    @Override
    public List<StockDTO> getStockList() {
        List<Stock> listStock = stockRepository.findAll();
        return stockMapper.stockListToDTOlist(listStock);
    }

    @Override
    public StockDTO getStockItemById(Integer id) {
        try {
            return stockMapper.stockToStockDTO(stockRepository.getById(id));
        } catch (Exception e) {
            throw new StockNotFoundException("Invalid stock item id");
        }
    }
}
