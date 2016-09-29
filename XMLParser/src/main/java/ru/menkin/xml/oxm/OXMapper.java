package ru.menkin.xml.oxm;

import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.*;

import javax.xml.bind.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class OXMapper {
    private static Logger logger = Logger.getLogger(OXMapper.class);

    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }

    public void setUnmarshaller(Unmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    public byte[] marshal(Object object) throws Exception {
        ByteArrayOutputStream os = null;
        byte[] result = null;
        try {
            os = new ByteArrayOutputStream();
            marshaller.marshal(object, new StreamResult(os));
            result = os.toByteArray();
        } catch (Exception exc) {
            logger.info("При маршаллинге произошла ошибка!", exc);
            throw exc;
        } finally {
            if (null != os)
                os.close();
        }
        return result;
    }

    public Object unmarshal(byte[] data) throws Exception {
        Object object = null;
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(data);
            object = unmarshaller.unmarshal(new StreamSource(is));
        } catch (Exception exc) {
            logger.info("При маршаллинге произошла ошибка!", exc);
            throw exc;
        } finally {
            if (null != is) { is.close(); }
        }
        return object;
    }
}
