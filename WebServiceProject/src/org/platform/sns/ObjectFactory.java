
package org.platform.sns;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.platform.sns package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Sayhello_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "sayhello");
    private final static QName _BuildVirtualSensorResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "buildVirtualSensorResponse");
    private final static QName _SetSPlan_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "setSPlan");
    private final static QName _StopSPlanResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "stopSPlanResponse");
    private final static QName _GetDataResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "getDataResponse");
    private final static QName _GetData_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "getData");
    private final static QName _DiscoverySensorsAndMeasurements_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "DiscoverySensorsAndMeasurements");
    private final static QName _SayhelloResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "sayhelloResponse");
    private final static QName _SetSPlanResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "setSPlanResponse");
    private final static QName _SendCommandResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "sendCommandResponse");
    private final static QName _StopSPlan_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "stopSPlan");
    private final static QName _BuildVirtualSensor_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "buildVirtualSensor");
    private final static QName _DiscoverySensorsAndMeasurementsResponse_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "DiscoverySensorsAndMeasurementsResponse");
    private final static QName _SendCommand_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "sendCommand");
    private final static QName _SayhelloResponseReturn_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "return");
    private final static QName _BuildVirtualSensorArg1_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg1");
    private final static QName _SendCommandArg0_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg0");
    private final static QName _SendCommandArg2_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg2");
    private final static QName _DiscoverySensorsAndMeasurementsArg8_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg8");
    private final static QName _DiscoverySensorsAndMeasurementsArg9_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg9");
    private final static QName _DiscoverySensorsAndMeasurementsArg6_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg6");
    private final static QName _DiscoverySensorsAndMeasurementsArg7_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg7");
    private final static QName _DiscoverySensorsAndMeasurementsArg3_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg3");
    private final static QName _DiscoverySensorsAndMeasurementsArg5_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg5");
    private final static QName _DiscoverySensorsAndMeasurementsArg4_QNAME = new QName("http://interfaces.base.snps.osgi.org/", "arg4");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.platform.sns
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayhelloResponse }
     * 
     */
    public SayhelloResponse createSayhelloResponse() {
        return new SayhelloResponse();
    }

    /**
     * Create an instance of {@link Sayhello }
     * 
     */
    public Sayhello createSayhello() {
        return new Sayhello();
    }

    /**
     * Create an instance of {@link GetDataResponse }
     * 
     */
    public GetDataResponse createGetDataResponse() {
        return new GetDataResponse();
    }

    /**
     * Create an instance of {@link StopSPlanResponse }
     * 
     */
    public StopSPlanResponse createStopSPlanResponse() {
        return new StopSPlanResponse();
    }

    /**
     * Create an instance of {@link BuildVirtualSensor }
     * 
     */
    public BuildVirtualSensor createBuildVirtualSensor() {
        return new BuildVirtualSensor();
    }

    /**
     * Create an instance of {@link SendCommand }
     * 
     */
    public SendCommand createSendCommand() {
        return new SendCommand();
    }

    /**
     * Create an instance of {@link StopSPlan }
     * 
     */
    public StopSPlan createStopSPlan() {
        return new StopSPlan();
    }

    /**
     * Create an instance of {@link BuildVirtualSensorResponse }
     * 
     */
    public BuildVirtualSensorResponse createBuildVirtualSensorResponse() {
        return new BuildVirtualSensorResponse();
    }

    /**
     * Create an instance of {@link GetData }
     * 
     */
    public GetData createGetData() {
        return new GetData();
    }

    /**
     * Create an instance of {@link SetSPlan }
     * 
     */
    public SetSPlan createSetSPlan() {
        return new SetSPlan();
    }

    /**
     * Create an instance of {@link SetSPlanResponse }
     * 
     */
    public SetSPlanResponse createSetSPlanResponse() {
        return new SetSPlanResponse();
    }

    /**
     * Create an instance of {@link DiscoverySensorsAndMeasurements }
     * 
     */
    public DiscoverySensorsAndMeasurements createDiscoverySensorsAndMeasurements() {
        return new DiscoverySensorsAndMeasurements();
    }

    /**
     * Create an instance of {@link SendCommandResponse }
     * 
     */
    public SendCommandResponse createSendCommandResponse() {
        return new SendCommandResponse();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link DiscoverySensorsAndMeasurementsResponse }
     * 
     */
    public DiscoverySensorsAndMeasurementsResponse createDiscoverySensorsAndMeasurementsResponse() {
        return new DiscoverySensorsAndMeasurementsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sayhello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "sayhello")
    public JAXBElement<Sayhello> createSayhello(Sayhello value) {
        return new JAXBElement<Sayhello>(_Sayhello_QNAME, Sayhello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuildVirtualSensorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "buildVirtualSensorResponse")
    public JAXBElement<BuildVirtualSensorResponse> createBuildVirtualSensorResponse(BuildVirtualSensorResponse value) {
        return new JAXBElement<BuildVirtualSensorResponse>(_BuildVirtualSensorResponse_QNAME, BuildVirtualSensorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetSPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "setSPlan")
    public JAXBElement<SetSPlan> createSetSPlan(SetSPlan value) {
        return new JAXBElement<SetSPlan>(_SetSPlan_QNAME, SetSPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopSPlanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "stopSPlanResponse")
    public JAXBElement<StopSPlanResponse> createStopSPlanResponse(StopSPlanResponse value) {
        return new JAXBElement<StopSPlanResponse>(_StopSPlanResponse_QNAME, StopSPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "getDataResponse")
    public JAXBElement<GetDataResponse> createGetDataResponse(GetDataResponse value) {
        return new JAXBElement<GetDataResponse>(_GetDataResponse_QNAME, GetDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "getData")
    public JAXBElement<GetData> createGetData(GetData value) {
        return new JAXBElement<GetData>(_GetData_QNAME, GetData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DiscoverySensorsAndMeasurements }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "DiscoverySensorsAndMeasurements")
    public JAXBElement<DiscoverySensorsAndMeasurements> createDiscoverySensorsAndMeasurements(DiscoverySensorsAndMeasurements value) {
        return new JAXBElement<DiscoverySensorsAndMeasurements>(_DiscoverySensorsAndMeasurements_QNAME, DiscoverySensorsAndMeasurements.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayhelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "sayhelloResponse")
    public JAXBElement<SayhelloResponse> createSayhelloResponse(SayhelloResponse value) {
        return new JAXBElement<SayhelloResponse>(_SayhelloResponse_QNAME, SayhelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetSPlanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "setSPlanResponse")
    public JAXBElement<SetSPlanResponse> createSetSPlanResponse(SetSPlanResponse value) {
        return new JAXBElement<SetSPlanResponse>(_SetSPlanResponse_QNAME, SetSPlanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendCommandResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "sendCommandResponse")
    public JAXBElement<SendCommandResponse> createSendCommandResponse(SendCommandResponse value) {
        return new JAXBElement<SendCommandResponse>(_SendCommandResponse_QNAME, SendCommandResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopSPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "stopSPlan")
    public JAXBElement<StopSPlan> createStopSPlan(StopSPlan value) {
        return new JAXBElement<StopSPlan>(_StopSPlan_QNAME, StopSPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuildVirtualSensor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "buildVirtualSensor")
    public JAXBElement<BuildVirtualSensor> createBuildVirtualSensor(BuildVirtualSensor value) {
        return new JAXBElement<BuildVirtualSensor>(_BuildVirtualSensor_QNAME, BuildVirtualSensor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DiscoverySensorsAndMeasurementsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "DiscoverySensorsAndMeasurementsResponse")
    public JAXBElement<DiscoverySensorsAndMeasurementsResponse> createDiscoverySensorsAndMeasurementsResponse(DiscoverySensorsAndMeasurementsResponse value) {
        return new JAXBElement<DiscoverySensorsAndMeasurementsResponse>(_DiscoverySensorsAndMeasurementsResponse_QNAME, DiscoverySensorsAndMeasurementsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendCommand }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "sendCommand")
    public JAXBElement<SendCommand> createSendCommand(SendCommand value) {
        return new JAXBElement<SendCommand>(_SendCommand_QNAME, SendCommand.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = SayhelloResponse.class)
    public JAXBElement<String> createSayhelloResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, SayhelloResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = GetDataResponse.class)
    public JAXBElement<String> createGetDataResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, GetDataResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = StopSPlanResponse.class)
    public JAXBElement<String> createStopSPlanResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, StopSPlanResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg1", scope = BuildVirtualSensor.class)
    public JAXBElement<String> createBuildVirtualSensorArg1(String value) {
        return new JAXBElement<String>(_BuildVirtualSensorArg1_QNAME, String.class, BuildVirtualSensor.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg0", scope = SendCommand.class)
    public JAXBElement<String> createSendCommandArg0(String value) {
        return new JAXBElement<String>(_SendCommandArg0_QNAME, String.class, SendCommand.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg2", scope = SendCommand.class)
    public JAXBElement<String> createSendCommandArg2(String value) {
        return new JAXBElement<String>(_SendCommandArg2_QNAME, String.class, SendCommand.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg0", scope = StopSPlan.class)
    public JAXBElement<String> createStopSPlanArg0(String value) {
        return new JAXBElement<String>(_SendCommandArg0_QNAME, String.class, StopSPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = BuildVirtualSensorResponse.class)
    public JAXBElement<String> createBuildVirtualSensorResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, BuildVirtualSensorResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg1", scope = GetData.class)
    public JAXBElement<String> createGetDataArg1(String value) {
        return new JAXBElement<String>(_BuildVirtualSensorArg1_QNAME, String.class, GetData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg0", scope = GetData.class)
    public JAXBElement<String> createGetDataArg0(String value) {
        return new JAXBElement<String>(_SendCommandArg0_QNAME, String.class, GetData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg2", scope = GetData.class)
    public JAXBElement<String> createGetDataArg2(String value) {
        return new JAXBElement<String>(_SendCommandArg2_QNAME, String.class, GetData.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg0", scope = SetSPlan.class)
    public JAXBElement<String> createSetSPlanArg0(String value) {
        return new JAXBElement<String>(_SendCommandArg0_QNAME, String.class, SetSPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg8", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg8(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg8_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg9", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg9(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg9_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg6", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg6(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg6_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg7", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg7(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg7_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg1", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg1(String value) {
        return new JAXBElement<String>(_BuildVirtualSensorArg1_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg0", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg0(String value) {
        return new JAXBElement<String>(_SendCommandArg0_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg3", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg3(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg3_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg2", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg2(String value) {
        return new JAXBElement<String>(_SendCommandArg2_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg5", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg5(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg5_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "arg4", scope = DiscoverySensorsAndMeasurements.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsArg4(String value) {
        return new JAXBElement<String>(_DiscoverySensorsAndMeasurementsArg4_QNAME, String.class, DiscoverySensorsAndMeasurements.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = SetSPlanResponse.class)
    public JAXBElement<String> createSetSPlanResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, SetSPlanResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = SendCommandResponse.class)
    public JAXBElement<String> createSendCommandResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, SendCommandResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://interfaces.base.snps.osgi.org/", name = "return", scope = DiscoverySensorsAndMeasurementsResponse.class)
    public JAXBElement<String> createDiscoverySensorsAndMeasurementsResponseReturn(String value) {
        return new JAXBElement<String>(_SayhelloResponseReturn_QNAME, String.class, DiscoverySensorsAndMeasurementsResponse.class, value);
    }

}
