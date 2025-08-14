package com.nocturne.cafemanagerweb.xml;

import com.nocturne.cafemanagerweb.model.ChiTietDon;
import com.nocturne.cafemanagerweb.model.DonHang;
import com.nocturne.cafemanagerweb.model.Mon;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DonHangXML {

    private static final String FILE_NAME  = "DonHang.xml";
    private static final String FILE_NAME2 = "Thongke.xml";

    // ========= Helpers =========
    private static String safeStr(String s) { return s == null ? "" : s; }

    private static void writeToFile(Document doc, String filename) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        tf.transform(new DOMSource(doc), new StreamResult(new File(filename)));
    }

    private static Document newDoc() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        return builder.newDocument();
    }

    private static Document parseIfExists(String filename) throws Exception {
        File f = new File(filename);
        if (!f.exists()) return null;
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return builder.parse(f);
    }

    // ========= Ghi/Đọc DonHang.xml =========
    public static void ghi(List<DonHang> danhSach) {
        try {
            Document doc = newDoc();
            Element root = doc.createElement("DonHangs");
            doc.appendChild(root);

            for (DonHang dh : danhSach) {
                Element eDon = doc.createElement("DonHang");
                eDon.setAttribute("ma", safeStr(String.valueOf(dh.getMaDon())));
                eDon.setAttribute("ten", safeStr(dh.getTenKhach()));

                for (ChiTietDon ct : dh.getChiTiet()) {
                    Element eCT = doc.createElement("ChiTiet");
                    String tenMon = ct.getMon() != null ? safeStr(ct.getMon().getTen()) : "";
                    BigDecimal gia = (ct.getMon() != null && ct.getMon().getGia() != null)
                            ? ct.getMon().getGia() : BigDecimal.ZERO;

                    eCT.setAttribute("mon", tenMon);
                    eCT.setAttribute("gia", gia.toPlainString());
                    eCT.setAttribute("soLuong", String.valueOf(ct.getSoLuong()));
                    eDon.appendChild(eCT);
                }
                root.appendChild(eDon);
            }

            writeToFile(doc, FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<DonHang> doc() {
        List<DonHang> list = new ArrayList<>();
        try {
            Document doc = parseIfExists(FILE_NAME);
            if (doc == null) return list;

            NodeList ds = doc.getElementsByTagName("DonHang");
            for (int i = 0; i < ds.getLength(); i++) {
                Element eDon = (Element) ds.item(i);
                String ma        = eDon.getAttribute("ma");
                String tenKhach  = eDon.getAttribute("ten");

                DonHang dh = new DonHang(tenKhach, ma);

                NodeList dsCT = eDon.getElementsByTagName("ChiTiet");
                for (int j = 0; j < dsCT.getLength(); j++) {
                    Element eCT = (Element) dsCT.item(j);
                    String tenMonStr = eCT.getAttribute("mon");
                    String giaStr    = eCT.getAttribute("gia");
                    String slStr     = eCT.getAttribute("soLuong");

                    BigDecimal gia = BigDecimal.ZERO;
                    try { if (giaStr != null && !giaStr.isBlank()) gia = new BigDecimal(giaStr.trim()); }
                    catch (NumberFormatException ignore) {}

                    int soLuong = 0;
                    try { soLuong = Integer.parseInt(slStr.trim()); } catch (Exception ignore) {}

                    Mon mon = new Mon(null, tenMonStr, gia);
                    dh.themChiTiet(new ChiTietDon(mon, soLuong));
                }
                list.add(dh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ========= Ghi/Đọc Thongke.xml =========
    public static void ghiThongKe(List<DonHang> danhSach) {
        try {
            Document doc = newDoc();
            Element root = doc.createElement("ThongKe");
            doc.appendChild(root);

            for (DonHang dh : danhSach) {
                Element eDon = doc.createElement("DonHang");
                eDon.setAttribute("ma", safeStr(String.valueOf(dh.getMaDon())));
                eDon.setAttribute("ten", safeStr(dh.getTenKhach()));

                for (ChiTietDon ct : dh.getChiTiet()) {
                    Element eCT = doc.createElement("ChiTiet");
                    String tenMon = ct.getMon() != null ? safeStr(ct.getMon().getTen()) : "";
                    BigDecimal gia = (ct.getMon() != null && ct.getMon().getGia() != null)
                            ? ct.getMon().getGia() : BigDecimal.ZERO;

                    eCT.setAttribute("mon", tenMon);
                    eCT.setAttribute("gia", gia.toPlainString());
                    eCT.setAttribute("soLuong", String.valueOf(ct.getSoLuong()));
                    eDon.appendChild(eCT);
                }
                root.appendChild(eDon);
            }

            writeToFile(doc, FILE_NAME2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<DonHang> docThongKe() {
        List<DonHang> list = new ArrayList<>();
        try {
            Document doc = parseIfExists(FILE_NAME2);
            if (doc == null) return list;

            NodeList ds = doc.getElementsByTagName("DonHang");
            for (int i = 0; i < ds.getLength(); i++) {
                Element eDon = (Element) ds.item(i);
                String ma        = eDon.getAttribute("ma");
                String tenKhach  = eDon.getAttribute("ten");
                DonHang dh = new DonHang(tenKhach, ma);

                NodeList dsCT = eDon.getElementsByTagName("ChiTiet");
                for (int j = 0; j < dsCT.getLength(); j++) {
                    Element eCT = (Element) dsCT.item(j);
                    String tenMonStr = eCT.getAttribute("mon");
                    String giaStr    = eCT.getAttribute("gia");
                    String slStr     = eCT.getAttribute("soLuong");

                    BigDecimal gia = BigDecimal.ZERO;
                    try { if (giaStr != null && !giaStr.isBlank()) gia = new BigDecimal(giaStr.trim()); }
                    catch (NumberFormatException ignore) {}

                    int soLuong = 0;
                    try { soLuong = Integer.parseInt(slStr.trim()); } catch (Exception ignore) {}

                    Mon mon = new Mon(null, tenMonStr, gia);
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

