package diplom.buisness;

import diplom.data.exceptions.ScriptFailureException;

public class ScriptRunner {

    public void launchANSYS() {

    }

    private void runAPDL(String ansysPath, String directory, String jobname, String inputFile, String outputFile) {
        String callstring = String.format("\"{%s}\" -b -dir \"{%s}\" -np 2 -j \"{%s}\" -i \"{%s}\" -o \"{%s}\"",
                ansysPath, directory, jobname, inputFile, outputFile);
        try {
            Process process = new ProcessBuilder(callstring).start();
            int exitCode = process.waitFor();
        }
        catch (Exception e) {
            throw new ScriptFailureException(String.format("Скрипт прервался с ошибкой: {%s}", e.getMessage()));
        }
    }
}
