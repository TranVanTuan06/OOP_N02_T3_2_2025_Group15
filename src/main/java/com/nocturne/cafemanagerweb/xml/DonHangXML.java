package com.nocturne.cafemanagerweb.xml;

import com.nocturne.cafemanagerweb.model.*;
import java.io.File;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class DonHangXML {

    private static final String FILE_NAME = "DonHang.xml";
    private static final String FILE_NAME2 = "Thongke.xml";

    public static void ghi(List<DonHang> danhSach) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("DonHangs");
            doc.appendChild(root);

            for (DonHang dh : danhSach) {
                Element eDon = doc.createElement("DonHang");
                eDon.setAttribute("ma", String.valueOf(dh.getMaDon()));
                eDon.setAttribute("ten", dh.getTenKhach());

                for (ChiTietDon ct : dh.getChiTiet()) {
                    Element eCT = doc.createElement("ChiTiet");
                    eCT.setAttribute("mon", ct.getMon().getTenMon());
                    eCT.setAttribute("gia", String.valueOf(ct.getMon().getGia()));
                    eCT.setAttribute("soLuong", String.valueOf(ct.getSoLuong()));
                    eDon.appendChild(eCT);
                }

                root.appendChild(eDon);
            }

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(doc), new StreamResult(new File(FILE_NAME)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<DonHang> doc() {
        List<DonHang> list = new ArrayList<>();
        try {
            File f = new File(FILE_NAME);
            if (!f.exists()) {
                return list;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(f);

            NodeList ds = doc.getElementsByTagName("DonHang");
            for (int i = 0; i < ds.getLength(); i++) {
                Element eDon = (Element) ds.item(i);
                String ma = eDon.getAttribute("ma");
                String tenKhach = eDon.getAttribute("ten");
                DonHang dh = new DonHang(tenKhach, ma);

                NodeList dsCT = eDon.getElementsByTagName("ChiTiet");
                for (int j = 0; j < dsCT.getLength(); j++) {
                    Element eCT = (Element) dsCT.item(j);
                    String tenMon = eCT.getAttribute("mon");
                    double gia = Double.parseDouble(eCT.getAttribute("gia"));
                    int soLuong = Integer.parseInt(eCT.getAttribute("soLuong"));

                    Mon mon = new Mon(tenMon, gia);
                    dh.themChiTiet(new ChiTietDon(mon, soLuong));
                }

                list.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void ghiThongKe(List<DonHang> danhSach) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("ThongKe");
            doc.appendChild(root);

            for (DonHang dh : danhSach) {
                Element eDon = doc.createElement("DonHang");
                eDon.setAttribute("ma", String.valueOf(dh.getMaDon()));
                eDon.setAttribute("ten", dh.getTenKhach());

                for (ChiTietDon ct : dh.getChiTiet()) {
                    Element eCT = doc.createElement("ChiTiet");
                    eCT.setAttribute("mon", ct.getMon().getTenMon());
                    eCT.setAttribute("gia", String.valueOf(ct.getMon().getGia()));
                    eCT.setAttribute("soLuong", String.valueOf(ct.getSoLuong()));
                    eDon.appendChild(eCT);
                }

                root.appendChild(eDon);
            }

            Transformer tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");
            tf.transform(new DOMSource(doc), new StreamResult(new File(FILE_NAME2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<DonHang> docThongKe() {
        List<DonHang> list = new ArrayList<>();
        try {
            File f = new File(FILE_NAME2);
            if (!f.exists()) {
                return list;
            }

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(f);

            NodeList ds = doc.getElementsByTagName("DonHang");
            for (int i = 0; i < ds.getLength(); i++) {
                Element eDon = (Element) ds.item(i);
                String ma = eDon.getAttribute("ma");
                String tenKhach = eDon.getAttribute("ten");
                DonHang dh = new DonHang(tenKhach, ma);

                NodeList dsCT = eDon.getElementsByTagName("ChiTiet");
                for (int j = 0; j < dsCT.getLength(); j++) {
                    Element eCT = (Element) dsCT.item(j);
                    String tenMon = eCT.getAttribute("mon");
                    double gia = Double.parseDouble(eCT.getAttribute("gia"));
                    int soLuong = Integer.parseInt(eCT.getAttribute("soLuong"));

                    Mon mon = new Mon(tenMon, gia);
                    dh.themChiTiet(new ChiTietDon(mon, soLuong));
                }

                list.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
}
