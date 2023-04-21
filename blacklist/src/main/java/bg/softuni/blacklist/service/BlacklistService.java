package bg.softuni.blacklist.service;

import org.springframework.stereotype.Service;

@Service
public class BlacklistService {

    public boolean isBlacklisted(String IpAddress){

        //TODO: repo where admin can manage bl IPs
        return true;

    }
}
