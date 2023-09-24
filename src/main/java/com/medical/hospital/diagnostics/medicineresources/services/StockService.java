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
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import static com.medical.hospital.diagnostics.interfaces.StartConstants.*;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService implements StockServiceInterface {
    @Autowired
    private StockRepository stockRepository;
    private final StockMapper stockMapper;
    @Override
    public List<StockDTO> getStockList() {
        List<StockDTO> listStockDTO = new LinkedList<>();
        List<Stock> listStock = stockRepository.findAll();
        for (Stock stock: listStock) {
            URI foodURI = URI.create("/stock/" + stock.getId());
            StockDTO stockDTO = stockMapper.stockToStockDTO(stock);
            stockDTO.setName(HREF_LEFT + foodURI + HREF_MIDDLE + stockDTO.getName() + HREF_RIGHT);
            listStockDTO.add(stockDTO);
        }
        return listStockDTO;
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
