package com.dcasadevall.server;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * A grpc server which starts when {@link GrpcServer#start(BindableService[], int)} is called.
 * It blocked the current thread until such thread is terminated.
 */
public final class GrpcServer {
  public static void start(int port, BindableService[] services, String[] args) throws Exception {
    Options options = createOptions();
    CommandLineParser parser = new DefaultParser();
    CommandLine line;
    try {
      line = parser.parse(options, args);
    } catch (ParseException e) {
      System.err.println("Invalid command line: " + e.getMessage());
      printUsage(options);
      return;
    }

    if (line.hasOption("port")) {
      String portOption = line.getOptionValue("port");
      try {
        port =  Integer.parseInt(portOption);
      } catch (java.lang.NumberFormatException e) {
        System.err.println("Invalid port number: " + portOption);
        printUsage(options);
        return;
      }
    }

    final GrpcServer server = new GrpcServer();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        System.out.println("Shutting down");
        server.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }));
    server.start(services, port);
    System.out.format("Tabletop Combat Helper server listening on %d\n", port);
    server.blockUntilShutdown();
  }

  private Server server;

  private void start(BindableService[] services, int port) throws IOException {
    ServerBuilder builder = ServerBuilder.forPort(port);
    for (BindableService service : services) {
      builder.addService(service);
    }

    server = builder.build().start();
  }

  private void stop() throws Exception {
    server.shutdownNow();
    if (!server.awaitTermination(5, TimeUnit.SECONDS)) {
      System.err.println("Timed out waiting for server shutdown");
    }
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  private static Options createOptions() {
    Options options = new Options();

    // port
    options.addOption(Option.builder()
        .longOpt("port")
        .desc("The port on which the server listens.")
        .hasArg()
        .argName("port")
        .type(Integer.class)
        .build());

    return options;
  }

  private static void printUsage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("client",
        "A simple gRPC server for use with the Tabletop Combat Helper.", options, "", true);
  }
}

