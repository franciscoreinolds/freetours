export default class Tour {
    constructor(name, description, duration_hours, duration_minutes, location, languages, minCapacity, maxCapacity, category, route) {
        this.name = name;
        this.description = description;
        this.duration_hours = duration_hours;
        this.duration_minutes = duration_minutes;
        this.location = location;
        this.languages = languages;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.category = category;
        this.route = route;
    }
}