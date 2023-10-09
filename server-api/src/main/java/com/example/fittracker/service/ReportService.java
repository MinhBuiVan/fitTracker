package com.example.fittracker.service;

import com.example.fittracker.model.Report;
import com.example.fittracker.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;
    public Report saveReport(Report report) {
        return reportRepository.save(report);
    }
}
