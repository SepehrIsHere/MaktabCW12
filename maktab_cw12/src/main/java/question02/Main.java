package question02;

import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        DataSummery totalSummary = IntStream.range(1, 100)
                .boxed()
                .collect(DataSummery::new,
                        (summary, b) -> {
                            summary.min = Math.min(summary.min, b);
                            summary.max = Math.max(summary.max, b);
                            summary.sum += b;
                            summary.count++;
                            summary.avg = (double) summary.sum / summary.count;
                        }, ((dataSummary, dataSummary2) -> {
                            dataSummary.min = Math.min(dataSummary.min, dataSummary2.min);
                            dataSummary.max = Math.max(dataSummary.max, dataSummary2.max);
                            dataSummary.sum = dataSummary.sum + dataSummary2.sum;
                            dataSummary.avg = (double) dataSummary.sum / (dataSummary.count + dataSummary2.count);
                            dataSummary.count = dataSummary.count + dataSummary2.count;
                        }));
    }
}
