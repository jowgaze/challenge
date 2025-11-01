package com.challenge.services;

import com.challenge.domain.model.Channel;
import com.challenge.domain.model.Store;
import com.challenge.dto.IntervalDto;
import com.challenge.dto.TicketDto;
import com.challenge.dto.balance.BalanceResponseDto;
import com.challenge.dto.customer.InactiveCustomerDto;
import com.challenge.dto.product.TopProductResponseDto;
import com.challenge.dto.report.ReportDto;
import com.challenge.dto.report.ReportRequestDto;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ChallengeService service;

    public byte[] getReport(ReportRequestDto request) throws JRException {
        ReportDto reportDto = this.getReportDto(request);

        InputStream template = getClass().getResourceAsStream("/reports/report.jrxml");
        if (template == null)
            throw new JRException("Template report.jrxml não encontrado em /reports");

        JasperReport report = JasperCompileManager.compileReport(template);

        Map<String, Object> params = new HashMap<>();
        params.put("storeName", reportDto.getStoreName());
        params.put("channelName", reportDto.getChannelName());
        params.put("start", reportDto.getStart());
        params.put("end", reportDto.getEnd());

        params.put("balances", new JRBeanCollectionDataSource(reportDto.getBalances()));
        params.put("topProducts", new JRBeanCollectionDataSource(reportDto.getTopProducts()));
        params.put("tickets", new JRBeanCollectionDataSource(reportDto.getTickets()));
        params.put("inactiveCustomers", new JRBeanCollectionDataSource(reportDto.getInactiveCustomer()));

        // Calcula total geral do balanço
        double total = reportDto.getBalances() != null
                ? reportDto.getBalances().stream()
                .filter(b -> b.getAmount() != null)
                .mapToDouble(b -> b.getAmount().doubleValue())
                .sum()
                : 0;
        params.put("totalBalance", total);

        JasperPrint print = JasperFillManager.fillReport(report, params, new JREmptyDataSource());
        return JasperExportManager.exportReportToPdf(print);
    }

    private ReportDto getReportDto(ReportRequestDto request){
        IntervalDto interval = new IntervalDto(request.getStart(), request.getEnd());

        String storeName = "Todos";
        if (request.getStoreId() != 0)
            storeName = service.getStores()
                    .stream()
                    .filter(store -> store.getId().equals(request.getStoreId()))
                    .toList().get(0).getName();

        String channelName = "Todos";
        if (request.getChannelId() != 0)
            channelName = service.getChannels()
                    .stream()
                    .filter(channel -> channel.getId().equals(request.getChannelId()))
                    .toList().get(0).getName();

        LocalDate start = LocalDate.parse(request.getStart().toString().substring(0, 10));
        LocalDate end = LocalDate.parse(request.getEnd().toString().substring(0, 10));

        List<BalanceResponseDto> balances = service.getBalance(request.getStoreId(), interval);
        List<TopProductResponseDto> topProducts = service.getTopProducts(request.getStoreId(), request.getChannelId(), interval);
        List<TicketDto> tickets = service.getTicket(request.getStoreId(), request.getChannelId(), interval);
        List<InactiveCustomerDto> inactiveCustomer = service.getInactiveCustomers(request.getStoreId(), request.getChannelId());

        return new ReportDto(storeName, channelName, start, end, balances, topProducts, tickets, inactiveCustomer);
    }
}
