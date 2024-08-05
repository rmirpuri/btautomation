package test.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;

public class DockerManager {

    private String hubContainerName = "selenium-hub";
    private String chromeContainerName = "btautomation_chrome_1";
    private String firefoxContainerName = "btautomation_firefox_1";
    private String edgeContainerName = "btautomation_edge_1";
    private String networkName = "simple-form-net";
    private String dockerComposeDir = "/Users/rajiv/Documents/Resume/beyondtrust/uiautomationexercise/btautomation/btuiautomation";

    public DockerManager(String dockerComposeDir) {
        this.dockerComposeDir = dockerComposeDir;
    }

    public void setupDockerEnvironment() throws IOException, InterruptedException {
        File composeDirectory = new File(dockerComposeDir);
        if (!composeDirectory.exists() || !composeDirectory.isDirectory()) {
            throw new IOException("Specified directory does not exist or is not a directory: " + dockerComposeDir);
        }

        // Check if the network is running
        if (isNetworkRunning(networkName)) {
            // Remove the running network
            removeNetwork(networkName);
        }

        // Create the network
        createNetwork(networkName);

        // Check if the containers are running and stop/remove them
        manageContainer(hubContainerName);
        manageContainer(chromeContainerName);
        manageContainer(firefoxContainerName);
        manageContainer(edgeContainerName);

        // Start the containers using Docker Compose
        startContainersWithDockerCompose(composeDirectory);
    }

    private boolean isNetworkRunning(String networkName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("docker", "network", "ls", "-q", "--filter", "name=" + networkName);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine();
            process.waitFor();
            return line != null && !line.isEmpty();
        }
    }

    private void removeNetwork(String networkName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("docker", "network", "rm", networkName);
        Process process = processBuilder.start();
        process.waitFor();
    }

    private void createNetwork(String networkName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("docker", "network", "create", networkName);
        Process process = processBuilder.start();
        process.waitFor();
    }

    private boolean isContainerRunning(String containerName) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("docker", "ps", "-q", "--filter", "name=" + containerName);
        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = reader.readLine();
            process.waitFor();
            return line != null && !line.isEmpty();
        }
    }

    private void stopAndRemoveContainer(String containerName) throws IOException, InterruptedException {
        ProcessBuilder stopProcessBuilder = new ProcessBuilder("docker", "stop", containerName);
        Process stopProcess = stopProcessBuilder.start();
        stopProcess.waitFor();

        ProcessBuilder rmProcessBuilder = new ProcessBuilder("docker", "rm", containerName);
        Process rmProcess = rmProcessBuilder.start();
        rmProcess.waitFor();
    }

    private void manageContainer(String containerName) throws IOException, InterruptedException {
        if (isContainerRunning(containerName)) {
            stopAndRemoveContainer(containerName);
        }
    }

    private void startContainersWithDockerCompose(File composeDirectory) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("docker-compose", "up", "-d");
        processBuilder.directory(composeDirectory); // Specify the correct directory
        Process process = processBuilder.start();
        process.waitFor();
    }

}
