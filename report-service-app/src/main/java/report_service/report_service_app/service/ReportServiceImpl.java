package report_service.report_service_app.service;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReportServiceImpl {


    private ScoreServiceClient scoreServiceClient;

    private ScoreServiceClient scoreServiceClient;

    public int calculateMean(int studentRegNo) {
        Subject subject = scoreServiceClient.getSubjectByStudentRegNo(studentRegNo);
        if (subject == null) {
            // Handle case where subject is not found for studentRegNo
            return 0; // Or throw an exception or return an appropriate value
        }
        int[] scores = {subject.getEnglishScore(), subject.getMathematicsScore(),
                subject.getPhysicsScore(), subject.getComputerScore(),
                subject.getChemistryScore()};
        int sum = Arrays.stream(scores).sum();
        return sum / scores.length;
    }

    public int calculateMedian(int studentRegNo) {
        Subject subject = scoreServiceClient.getSubjectByStudentRegNo(studentRegNo);
        if (subject == null) {
            // Handle case where subject is not found for studentRegNo
            return 0; // Or throw an exception or return an appropriate value
        }
        int[] scores = {subject.getEnglishScore(), subject.getMathematicsScore(),
                subject.getPhysicsScore(), subject.getComputerScore(),
                subject.getChemistryScore()};
        Arrays.sort(scores);
        int middle = scores.length / 2;
        if (scores.length % 2 == 0) {
            return (scores[middle - 1] + scores[middle]) / 2;
        } else {
            return scores[middle];
        }
    }

    public int calculateMode(int studentRegNo) {
        Subject subject = scoreServiceClient.getSubjectByStudentRegNo(studentRegNo);
        if (subject == null) {
            // Handle case where subject is not found for studentRegNo
            return 0; // Or throw an exception or return an appropriate value
        }
        int[] scores = {subject.getEnglishScore(), subject.getMathematicsScore(),
                subject.getPhysicsScore(), subject.getComputerScore(),
                subject.getChemistryScore()};
        int maxCount = 0;
        int mode = 0;
        for (int i = 0; i < scores.length; ++i) {
            int count = 0;
            for (int j = 0; j < scores.length; ++j) {
                if (scores[j] == scores[i])
                    ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                mode = scores[i];
            }
        }
        return mode;
    }
}
