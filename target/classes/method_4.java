public String getCalenderName() {
    switch(type) {
        case COUNTRY:
            return country == null ? name : country.getName() + HOLIDAY_CALENDAR;
        case CCP:
            return ccp == null ? name : ccp.getName() + " CCP" + HOLIDAY_CALENDAR;
        case EXCHANGE:
            return exchange == null ? name : exchange.getName() + HOLIDAY_CALENDAR;
        case TENANT:
            return tenant == null ? name : tenant.getName() + HOLIDAY_CALENDAR;
            default:return name;
    }
}