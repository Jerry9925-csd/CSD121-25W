import sqlite3

def get_connection():
    """Returns a connection object for the application database, 
        with the row factory set to Row so that row data can be referenced using
        either index or column names"""
    connection = sqlite3.connect("data.sqlite")

    # Allow for indexing of rows using either integers or column names
    # See https://docs.python.org/3/library/sqlite3.html#row-objects
    connection.row_factory = sqlite3.Row  

    # Enforce referential entegrity
    cursor = connection.cursor()
    cursor.execute("PRAGMA foreign_keys = ON")

    return connection

def get_user(username):
    """Gets the user with the given username as a dict containing 
        the keys 'username' and 'password_hash'"""
    # TODO: Complete this method as per the docstring above
    pass

def update_password(username, password_hash):
    """Updates the password hash for the given username"""
    # TODO: Complete this method as per the docstring above 
    pass

def add_person(data):
    """Inserts a new person row based on the given data (must be a dict with keys corresponding to the
        column names of the person table).
        The person data may also include a 'phone_numbers' array that contains any number of 
        phone number dicts of the form {'number','label'}
        """
    with get_connection() as cnx:
        cursor = cnx.cursor()
        sql = """INSERT INTO person 
                (first_name, last_name, birthday, email,
                address_line1, address_line2, city, prov, country, postcode)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """
        cursor.execute(sql, [
            data['first_name'],
            data['last_name'],
            data['birthday'],
            data['email'],
            data['address_line1'],
            data['address_line2'],
            data['city'],
            data['prov'],
            data['country'],
            data['postcode']
        ])

        # TODO: Insert the person's phone numbers too, and make sure to do this in the context of a transaction

def delete_person(id):
    """Deletes the person with the given id from the person table
        id must be an id that exists in the person table"""
    with get_connection() as cnx:
        cursor = cnx.cursor()
        sql = """DELETE FROM person WHERE person_id = ?"""
        return cursor.execute(sql, [id])

PERSON_SORTABLE_FIELDS = ('person_id','first_name','last_name','birthday','email')
PERSON_SORTABLE_FIELD_HEADINGS = ('ID','First Name','Last Name','Birthday','Email')
def get_people_list(order_by):

    assert order_by in PERSON_SORTABLE_FIELDS, "The order_by argument must be one of: " + ", ".join(PERSON_SORTABLE_FIELDS)

    with get_connection() as cnx:
        cursor = cnx.cursor()
        # TODO: Update this query to include phone number information as per lab instructions
        sql = """SELECT person_id, first_name, last_name, birthday, email,
                        address_line1, address_line2, city, prov, country, postcode
                    FROM person"""

        if order_by:
            sql += " ORDER BY " + order_by

        results = cursor.execute(sql).fetchall()

        people = []
        # TODO: Update this loop so that it correctly adds a phone number list to each person
        for r in results:
            person = {
                'person_id': r['person_id'],
                'first_name': r['first_name'],
                'last_name': r['last_name'],
                'birthday': r['birthday'],
                'email': r['email'],
                'address_line1': r['address_line1'],
                'address_line2': r['address_line2'],
                'city': r['city'],
                'prov': r['prov'],
                'country': r['country'],
                'postcode': r['postcode']
            }
            people.append(person)

        return people

def get_person_ids():
    """Returns a list of the person ids that exist in the database"""
    with get_connection() as cnx:
        cursor = cnx.cursor()
        sql = """SELECT person_id FROM person"""
        return [ row[0] for row in cursor.execute(sql).fetchall() ]
