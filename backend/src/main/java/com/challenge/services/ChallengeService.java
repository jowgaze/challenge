package com.challenge.services;

import com.challenge.domain.model.Channel;
import com.challenge.domain.model.Store;
import com.challenge.dto.IntervalDto;
import com.challenge.dto.TicketDto;
import com.challenge.dto.balance.BalanceResponseDto;
import com.challenge.dto.channel.ChannelMapper;
import com.challenge.dto.channel.ChannelResponseDto;
import com.challenge.dto.customer.InactiveCustomerDto;
import com.challenge.dto.store.StoreMapper;
import com.challenge.dto.product.TopProductResponseDto;
import com.challenge.dto.store.StoreResponseDto;
import com.challenge.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ChallengeService {
    private final SaleRepository saleRepository;
    private final ProductSaleRepository productSaleRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    private final ChannelRepository channelRepository;

    @Transactional(readOnly = true)
    public List<BalanceResponseDto> getBalance(Long storeId, IntervalDto request) {
        storeId = isZero(storeId);
        List<BalanceResponseDto> balances = saleRepository.findTotalSales(storeId, request.start(), request.end());
        balances.add(getTotalBalance(balances));
        return balances;
    }

    @Transactional(readOnly = true)
    public List<TopProductResponseDto> getTopProducts(Long storeId, Long channelId, IntervalDto interval){
        storeId = isZero(storeId);
        channelId = isZero(channelId);

        AtomicLong count = new AtomicLong(1);

        return productSaleRepository.findTopProduct(storeId, channelId, interval.start(), interval.end())
                .stream()
                .peek(product -> product.setPosition(count.getAndIncrement()))
                .toList();
    }

    public List<TicketDto> getTicket(Long storeId, Long channelId, IntervalDto request) {
        storeId = isZero(storeId);
        channelId = isZero(channelId);

        return saleRepository.findTicketByChannelAndStore(storeId, channelId, request.start(), request.end());
    }

    @Transactional(readOnly = true)
    public List<InactiveCustomerDto> getInactiveCustomers(Long storeId, Long channelId){
        channelId = isZero(channelId);
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return customerRepository.findInactiveCustomers(storeId, channelId, thirtyDaysAgo);
    }

    public List<StoreResponseDto> getStores(){
        List<Store> stores = storeRepository.findAll();

        return stores.stream()
                .map(StoreMapper::toDto)
                .toList();
    }

    public List<ChannelResponseDto> getChannels(){
        List<Channel> channels = channelRepository.findAll();

        return channels.stream()
                .map(ChannelMapper::toDto)
                .toList();
    }

    private BalanceResponseDto getTotalBalance(List<BalanceResponseDto> balances) {
        Double totalAmount = balances.stream()
                .mapToDouble(balance -> balance.getAmount().doubleValue())
                .sum();

        return new BalanceResponseDto("Total", totalAmount);
    }

    private Long isZero(Long id){
        if (id == 0) return null;
        return id;
    }
}
