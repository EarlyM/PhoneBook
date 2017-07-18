package ua.phonebook.dao.filestorage;

import ua.phonebook.dao.ContactDAO;
import ua.phonebook.model.entity.Contact;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ContactFileStorage implements ContactDAO {

    private static Integer CONTACT_COUNT;

    private File contactFilePath;

    public ContactFileStorage(File contactFilePath) {
        this.contactFilePath = contactFilePath;
        try(RandomAccessFile raf = new RandomAccessFile(contactFilePath, "r")) {
            String result = null;
            long startIdx = contactFilePath.length();
            if(startIdx == 0){
                CONTACT_COUNT = 0;
            } else {
                if ((raf.readLine()).getBytes().length == startIdx) {
                    CONTACT_COUNT = 1;
                } else {
                    while (result == null || result.length() == 0) {
                        raf.seek(startIdx--);
                        raf.readLine();
                        result = raf.readLine();
                    }
                    String count = result.split(",")[0];
                    CONTACT_COUNT = Integer.parseInt(count);
                }
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Contact findById(Long id) {
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(contactFilePath.toURI()), Charset.forName("UTF-8"))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                String[] contactInfo = temp.split(",");
                if (Long.parseLong(contactInfo[0]) == id) {
                    return buildContact(contactInfo);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Contact contact) {
        String path = contactFilePath.getAbsolutePath();
        File tempFile = new File(path + ".tmp");
        contactFilePath.renameTo(tempFile);
        File newFile = new File(path);
        try(BufferedReader bufferedReader = Files.newBufferedReader(tempFile.toPath());
            BufferedWriter bufferedWriter = Files.newBufferedWriter(newFile.toPath())) {
            newFile.createNewFile();
            String temp;
            String[] contactInfo;
            while ((temp = bufferedReader.readLine()) != null){
                contactInfo = temp.split(",");
                if(contact.getId() == Long.parseLong(contactInfo[0])){
                    temp = contact.toString();
                }
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.delete();
    }

    @Override
    public void delete(Long id) {
        String path = contactFilePath.getAbsolutePath();
        File tempFile = new File(path + ".tmp");
        contactFilePath.renameTo(tempFile);
        File newFile = new File(path);
        try(BufferedReader bufferedReader = Files.newBufferedReader(tempFile.toPath());
            BufferedWriter bufferedWriter = Files.newBufferedWriter(newFile.toPath())) {
            newFile.createNewFile();
            String temp;
            String[] contactInfo;
            while ((temp = bufferedReader.readLine()) != null){
                contactInfo = temp.split(",");
                if(id == Long.parseLong(contactInfo[0])){
                    continue;
                }
                bufferedWriter.write(temp);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tempFile.delete();
    }

    @Override
    public void create(Contact contact) {
        try(BufferedWriter writer = Files.newBufferedWriter(contactFilePath.toPath(), StandardOpenOption.APPEND)) {
            contact.setId(++CONTACT_COUNT);
            writer.write(contact.toString());
            writer.newLine();
            writer.flush();
            writer.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long getContactCount(String username, String search, String field) {
        Long count = 0L;
        try(BufferedReader reader = Files.newBufferedReader(contactFilePath.toPath())) {
            int searchField = null == field ? 0 : field.equals(ContactDAO.NAME) ? 2 : field.equals(ContactDAO.SURNAME) ? 1 : field.equals(ContactDAO.MOBILE_PHONE) ? 4 : 0;
            String temp;
            while ((temp = reader.readLine()) != null){
                String[] contactInfo = temp.split(",");
                if(searchField == 0){
                    if(username.equals(contactInfo[8])){
                        count++;
                    }
                } else {
                    if(username.equals(contactInfo[8]) && contactInfo[searchField].indexOf(search) > -1){
                        count++;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public List<Contact> getContactsByCriterion(int from, int to, String username, String search, String field) {
        List<Contact> contacts = new ArrayList<>();
        int searchField = null == field ? 0 : field.equals(ContactDAO.NAME) ? 2 : field.equals(ContactDAO.SURNAME) ? 1 : field.equals(ContactDAO.MOBILE_PHONE) ? 4 : 0;
        try(BufferedReader reader = Files.newBufferedReader(contactFilePath.toPath())) {
            String temp;
            String[] contactInfo;
            Contact contact;
            int count = 0;
            while ((temp = reader.readLine()) != null && count < to){
                contactInfo = temp.split(",");
                if(searchField == 0 && username.equals(contactInfo[8])){
                    if(count < from){
                        count++;
                        continue;
                    }
                    contact = buildContact(contactInfo);
                    contacts.add(contact);
                    count++;
                } else if(username.equals(contactInfo[8]) && contactInfo[searchField].indexOf(search) > -1) {
                    if(count < from){
                        count++;
                        continue;
                    }
                    contact = buildContact(contactInfo);
                    contacts.add(contact);
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    private Contact buildContact(String[] contactInfo){
        Contact contact = new Contact();
        contact.setId(Long.parseLong(contactInfo[0]));
        contact.setSurname(contactInfo[1]);
        contact.setName(contactInfo[2]);
        contact.setPatronymic(contactInfo[3]);
        contact.setMobilePhone(contactInfo[4]);
        contact.setHomePhone(contactInfo[5]);
        contact.setAddress(contactInfo[6]);
        contact.seteMail(contactInfo[7]);
        contact.setUsername(contactInfo[8]);
        return contact;
    }

}
