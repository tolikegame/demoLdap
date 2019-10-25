package com.example.demo.service.impl;

import com.example.demo.Blowfish;
import com.example.demo.model.LdapUser;
import com.example.demo.model.OfUser;
import com.example.demo.repository.LdapUserRepository;
import com.example.demo.repository.OfRepository;
import com.example.demo.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

@Service
public class LdapServiceImpl implements LdapService {
    
    @Autowired
    LdapUserRepository ldapUserRepository;
    @Autowired
    LdapTemplate ldapTemplate;
    @Autowired
    OfRepository ofRepository;

    @Override
    public boolean saveLdap() {
        List<LdapUser> ldapUsers = ldapUserRepository.findAll();

        //存入openfireDB
        String openfireKey = "q5eJZ2DnMI55yTp";
        String noEncryPWD = "1234";
        OfUser ofUser = new OfUser();
        for(LdapUser ldapUser:ldapUsers){
            ofUser.setUsername(ldapUser.getAccount());
            //Base64
            Base64.Encoder encoder = Base64.getEncoder();
            String text = ldapUser.getAccount();
            byte[] textByte = new byte[0];
            try {
                textByte = text.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String encodedText = encoder.encodeToString(textByte);


            Blowfish _encoder=new Blowfish(openfireKey);
            String encodedStr=_encoder.encryptString(encodedText);

//            Blowfish blowFish = new Blowfish(passWordKey); //根据加密key初始化
//            noEncryPWD = blowFish.encryptString(noEncryPWD); //加密
            ofUser.setEncryptedpassword(encodedStr);
            ofUser.setName(ldapUser.getName());
            ofRepository.save(ofUser);
        }

        return true;
    }
}
