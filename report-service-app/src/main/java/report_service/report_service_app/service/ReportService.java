package report_service.report_service_app.service;


import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    int calculateMean(long studentRegNo);

    int calculateMedian(int studentRegNo);

    int calculateMode(int studentRegNo);



     /*teacher>>add student score acc to student regno
    score serv calculate the mean median mode of the score acc to student regno
    reportserv>>get the score data from scorerepo according to student regno*/
}
