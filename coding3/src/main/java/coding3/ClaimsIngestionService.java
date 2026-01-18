package coding3;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClaimsIngestionService {

    private static final Logger LOGGER = Logger.getLogger(ClaimsIngestionService.class.getName());

    private static final String BASE_DIR = "claims_processing";

    private static final Path INCOMING = Paths.get(BASE_DIR, "incoming_claims");
    private static final Path PROCESSING = Paths.get(BASE_DIR, "processing_claims");
    private static final Path PROCESSED = Paths.get(BASE_DIR, "processed_claims");
    private static final Path ERROR = Paths.get(BASE_DIR, "error_claims");
    private static final Path ARCHIVE = Paths.get(BASE_DIR, "claims_archive");

    private static final DateTimeFormatter ARCHIVE_FORMAT =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static void main(String[] args) {
        try {
            initializeDirectories();
            monitorIncomingDirectory();
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "System failure", e);
        }
    }

    /**
     * FR7: Ensure required directories exist
     */
    private static void initializeDirectories() throws IOException {
        Files.createDirectories(INCOMING);
        Files.createDirectories(PROCESSING);
        Files.createDirectories(PROCESSED);
        Files.createDirectories(ERROR);
        Files.createDirectories(ARCHIVE);

        LOGGER.info("Directory structure validated/created.");
    }

    /**
     * FR1: Continuously monitor incoming_claims
     */
    private static void monitorIncomingDirectory() throws IOException, InterruptedException {
        LOGGER.info("Monitoring incoming_claims directory...");

        while (true) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(INCOMING)) {
                for (Path file : stream) {
                    if (Files.isRegularFile(file)) {
                        processFile(file);
                    }
                }
            }
            // Idle wait to reduce CPU usage (NFR6)
            Thread.sleep(2000);
        }
    }

    /**
     * Core processing logic
     */
    private static void processFile(Path file) {
        String fileName = file.getFileName().toString().toLowerCase();

        try {
            if (isCorrupt(fileName)) {
                moveFile(file, ERROR);
                LOGGER.warning("Corrupt file routed to error_claims: " + fileName);
            } else if (isValidClaim(fileName)) {
                Path processingFile = moveFile(file, PROCESSING);
                simulateProcessing(processingFile);

                Path processedFile = moveFile(processingFile, PROCESSED);
                archiveFile(processedFile);

                LOGGER.info("Successfully processed and archived: " + fileName);
            } else {
                // Unknown files treated as errors
                moveFile(file, ERROR);
                LOGGER.warning("Unknown file routed to error_claims: " + fileName);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing file: " + fileName, e);
        }
    }

    /**
     * FR3: File identification rules
     */
    private static boolean isValidClaim(String fileName) {
        return fileName.startsWith("claim_");
    }

    private static boolean isCorrupt(String fileName) {
        return fileName.contains("unreadable") || fileName.endsWith(".corrupt");
    }

    /**
     * Atomic move where possible (NFR2)
     */
    private static Path moveFile(Path source, Path targetDir) throws IOException {
        Path target = targetDir.resolve(source.getFileName());
        return Files.move(source, target, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
    }

    /**
     * Simulated processing step
     */
    private static void simulateProcessing(Path file) {
        try {
            Thread.sleep(500); // Simulate processing time
        } catch (InterruptedException ignored) {
        }
    }

    /**
     * FR6: Timestamped archiving
     */
    private static void archiveFile(Path processedFile) throws IOException {
        String timestamp = LocalDateTime.now().format(ARCHIVE_FORMAT);
        Path archiveDir = ARCHIVE.resolve(timestamp);

        Files.createDirectories(archiveDir);
        Files.move(processedFile, archiveDir.resolve(processedFile.getFileName()),
                StandardCopyOption.REPLACE_EXISTING);
    }
}

