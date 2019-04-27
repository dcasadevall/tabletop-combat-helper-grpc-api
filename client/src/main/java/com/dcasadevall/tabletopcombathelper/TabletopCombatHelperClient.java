package com.dcasadevall.tabletopcombathelper;

import com.dcasadevall.tabletopcombathelper.campaigns.CampaignProto;
import com.dcasadevall.tabletopcombathelper.campaigns.CampaignServiceGrpc;
import com.dcasadevall.tabletopcombathelper.campaigns.CampaignServiceGrpc.CampaignServiceBlockingStub;
import com.dcasadevall.tabletopcombathelper.campaigns.DeleteCampaignRequest;
import com.dcasadevall.tabletopcombathelper.campaigns.ListCampaignsResponse;
import com.dcasadevall.tabletopcombathelper.campaigns.SaveCampaignRequest;
import com.google.protobuf.Empty;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/** A client application which calls the Tabletop Combat Helper API over gRPC. */
public final class TabletopCombatHelperClient {
  private static final String DEFAULT_HOST = "localhost";
  private static final String DEFAULT_PORT = "8000";

  public static void main(String[] args) throws Exception {
    Options options = createOptions();
    CommandLineParser parser = new DefaultParser();
    CommandLine params;
    try {
      params = parser.parse(options, args);
    } catch (ParseException e) {
      System.err.println("Invalid command line: " + e.getMessage());
      printUsage(options);
      return;
    }

    String operation = params.getOptionValue("operation", "list");
    String host = params.getOptionValue("host", "localhost");
    String port = params.getOptionValue("port", "8000");
    String address = String.format("%s:%s", host, port);

    // Create gRPC stub.
    CampaignServiceGrpc.CampaignServiceBlockingStub stub =
        CampaignServiceGrpc.newBlockingStub(
            ManagedChannelBuilder.forTarget(address).usePlaintext(true).build());

    if ("listCampaigns".equals(operation)) {
      listCampaigns(stub);
    } else if ("createCampaign".equals(operation)) {
      createCampaign(stub);
    } else if ("removeCampaign".equals(operation)) {
      removeCampaign(stub);
    }
  }

  /**
   * Lists all campaigns.
   *
   * @param campaignServiceStub a client stub to call Campaign service.
   */
  private static void listCampaigns(
      CampaignServiceGrpc.CampaignServiceBlockingStub campaignServiceStub) {
    ListCampaignsResponse campaignsResponse =
        campaignServiceStub.listCampaigns(Empty.getDefaultInstance());
    System.out.println(campaignsResponse);
  }

  /**
   * Creates a new campaign.
   *
   * @param campaignServiceStub a client stub to call Campaign service.
   */
  private static void createCampaign(
      CampaignServiceGrpc.CampaignServiceBlockingStub campaignServiceStub) {
    CampaignProto campaignProto =
        campaignServiceStub.saveCampaign(
            SaveCampaignRequest.newBuilder()
                .setCampaign(CampaignProto.newBuilder().setName("Test Campaign").build())
                .build());

    System.out.println(campaignProto);
  }

  /**
   * Removes one existing campaign from the service.
   *
   * @param campaignServiceStub a client stub to call Campaign service.
   */
  private static void removeCampaign(CampaignServiceBlockingStub campaignServiceStub) {
    ListCampaignsResponse campaignsResponse =
        campaignServiceStub.listCampaigns(Empty.getDefaultInstance());
    if (campaignsResponse.getCampaignsCount() == 0) {
      System.out.println("No Campaigns to delete.");
      return;
    }

    campaignServiceStub.deleteCampaign(
        DeleteCampaignRequest.newBuilder()
            .setCampaignId(campaignsResponse.getCampaigns(0).getCampaignId())
            .build());
  }

  private static Options createOptions() {
    Options options = new Options();

    // operation
    options.addOption(
        Option.builder()
            .longOpt("operation")
            .desc("The operation to perform: listCampaigns|createCampaign|removeCampaign")
            .hasArg()
            .required()
            .argName("op")
            .type(String.class)
            .build());

    options.addOption(
        Option.builder()
            .longOpt("host")
            .desc(String.format("Host to be used. By default: %s", DEFAULT_HOST))
            .hasArg()
            .argName("h")
            .type(String.class)
            .build());

    options.addOption(
        Option.builder()
            .longOpt("port")
            .desc(String.format("Port to be used. By default: %s", DEFAULT_PORT))
            .hasArg()
            .argName("p")
            .type(String.class)
            .build());

    return options;
  }

  private static void printUsage(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp(
        "client",
        "A simple Tabletop Combat Helper gRPC client for use with Endpoints.",
        options,
        "",
        true);
  }
}
