from pydantic import BaseModel, EmailStr, Field, validator
from typing import Optional
from enum import Enum
from models.user import User

class UserType(str, Enum):
    customer = "customer"
    vendor = "vendor"

class User(BaseModel):
    Userid: int
    username: str = Field(..., min_length=1, max_length=100)
    password: str = Field(..., min_length=6)
    email: EmailStr
    fullname: str = Field(..., min_length=1)
    address: Optional[str] = None
    phonenumber: Optional[str] = None
    Usertype: UserType
    storename: Optional[str] = None
    storeaddress: Optional[str] = None
    oauth_token: str

    @validator('phonenumber')
    def validate_phonenumber(cls, v):
        if v and not v.isdigit():
            raise ValueError('Phone number must contain only digits')
        return v


class access(BaseModel):
    email: EmailStr
    username: str
    access: str




